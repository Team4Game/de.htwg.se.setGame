package de.htwg.se.setgame.util.persistence.hibernate;


import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.util.persistence.IGameDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.google.inject.Inject;

import java.util.LinkedList;
import java.util.List;

public class GameDao implements IGameDao {

    private Mapper mapper;

    @Inject
    public GameDao(IModelFactory modelFactory) {

        mapper = new Mapper(modelFactory);
    }

    @Override
    public void createOrUpdateGame(IGame game) {

        if (findGame(game.getId()) != null) {
            deleteGame(game);
        }
        PersistentGame persistenceGame = mapper.getPersistentGame(game);
        PersistentPlayer persistentPlayerOne = createUser(persistenceGame.getPlayerOne());
        game.setPlayerOne(persistentPlayerOne);
        PersistentPlayer persistentPlayerTwo = createUser(persistenceGame.getPlayerTwo());
        persistenceGame.setPlayerTwo(persistentPlayerTwo);
        List<PersistentCard> unusedCards = createCardsForGame((List<PersistentCard>) persistenceGame.getUnusedCards());
        persistenceGame.setUnusedCards(unusedCards);
        List<PersistentCard> cardsInField = createCardsForGame((List<PersistentCard>) persistenceGame.getCardsInField());
        persistenceGame.setCardsInField(cardsInField);
        saveGame(persistenceGame);

    }

    protected void deleteGame(IGame game) {
        Session session = SessionServiceHibernate.getSession();
        PersistentGame result = (PersistentGame) session.createCriteria(PersistentGame.class).add(Restrictions.eq("gameID", game.getId())).uniqueResult();
        session.close();
        if (result != null) {
            Session sessionDelete = SessionServiceHibernate.getSession();
            Transaction transaction = null;

            transaction = sessionDelete.beginTransaction();
            sessionDelete.delete(result);

            sessionDelete.flush();
            transaction.commit();
            sessionDelete.close();
        }
    }


    protected PersistentGame saveGame(PersistentGame persistenceGame) {
        Transaction transaction = null;
        Session session = SessionServiceHibernate.getSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(persistenceGame);
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.flush();
        transaction.commit();
        session.close();
        return persistenceGame;
    }

    protected List<PersistentCard> createCardsForGame(List<PersistentCard> unusedCards) {
        List<PersistentCard> result = new LinkedList<PersistentCard>();
        Session session = SessionServiceHibernate.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (PersistentCard card : unusedCards) {
                session.saveOrUpdate(card);
                result.add(card);
            }
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        return result;
    }


    protected PersistentPlayer createUser(PersistentPlayer newPlayer) {
        Transaction transaction = null;
        Session session = SessionServiceHibernate.getSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(newPlayer);
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.flush();
        transaction.commit();
        session.close();

        return newPlayer;
    }

    @Override
    public IGame findGame(String id) {
        IGame result = null;
        Session session = SessionServiceHibernate.getSession();
        PersistentGame persistentGame = (PersistentGame) session.createCriteria(PersistentGame.class).add(Restrictions.eq("gameID", id)).uniqueResult();
        if (persistentGame != null) {
            result = mapper.getGame(persistentGame);
        }
        session.flush();
        session.close();
        return result;
    }

    @Override
	public void closeDb() {
        SessionServiceHibernate.getSession().close();
    }

}