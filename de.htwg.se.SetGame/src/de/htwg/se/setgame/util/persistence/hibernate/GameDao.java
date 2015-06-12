package de.htwg.se.setgame.util.persistence.hibernate;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistence.IGameDao;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class GameDao implements IGameDao {

	private Session session;

	public GameDao() {
		this.session = new AnnotationConfiguration().configure()
				.buildSessionFactory().openSession();
	}

	@Override
	public void createOrUpdateGame(IGame game) {
		Mapper mapper = new Mapper();
		PersistentGame persGame = mapper.getPersistentGame(game);
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.saveOrUpdate(persGame);
			transaction.commit();
			System.out.println(persGame.getHibernateId());
		} catch (HibernateException ex) {
			if (transaction != null) {
				System.out.println(ex.toString());
				transaction.rollback();
			}
		}
	}

	@Override
	public IGame findGame(String id) {
		PersistentGame persGame = (PersistentGame) this.session.get(PersistentGame.class, id);
		Mapper mapper = new Mapper();
		return mapper.getGame(persGame);
	}

	@Override
	public void closeDb() {
		session.close();
	}

}