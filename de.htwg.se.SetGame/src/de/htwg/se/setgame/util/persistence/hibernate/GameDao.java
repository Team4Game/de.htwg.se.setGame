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
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.saveOrUpdate(game);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public IGame findGame(String id) {
		// TODO: todo
		return null;
	}

	@Override
	public void deleteGame(String id) {
		// TODO: todo
	}

}