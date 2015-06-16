package de.htwg.se.setgame.util.persistence.hibernate;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.se.setgame.SetGameModule;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.util.persistence.IGameDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.LinkedList;
import java.util.List;

public class GameDao implements IGameDao {

    Mapper mapper;
    @Inject
    public GameDao(IModelFactory modelFactory) {

        mapper = new Mapper(modelFactory);
    }

    @Override
    public void createOrUpdateGame(IGame game) {

        if (findGame(game.getId()) == null) {

            PersistentGame persistenceGame = mapper.getPersistentGame(game);
            PersistentPlayer persistentPlayerOne = createUser(persistenceGame.getPlayerOne());
            game.setPlayerOne(persistentPlayerOne);
            PersistentPlayer persistentPlayerTwo = createUser(persistenceGame.getPlayerTwo());
            persistenceGame.setPlayerTwo(persistentPlayerTwo);
            List<PersistentCard> unusedCards = createCardsForGame((List<PersistentCard>) persistenceGame.getUnusedCards());
            persistenceGame.setUnusedCards(unusedCards);
            List<PersistentCard> cardsInField = createCardsForGame((List<PersistentCard>) persistenceGame.getCardsInField());
            persistenceGame.setCardsInField(cardsInField);
            PersistentGame pGame = saveGame(persistenceGame);
        } else {
            updateGame(game);
        }
    }

    private void updateGame(IGame game) {
        PersistentGame persistentGame = mapper.getPersistentGame(game);
        Session session = SessionServiceHibernate.getSession();
        PersistentGame result = (PersistentGame) session.createCriteria(PersistentGame.class).add(Restrictions.eq("gameID", game.getId())).uniqueResult();
        result.getPlayerOne().setCounter(persistentGame.getPlayerOne().getCounter());
        result.getPlayerTwo().setCounter(persistentGame.getPlayerTwo().getCounter());
        result.getCardsInField().clear();
        result.setCardsInField(createCardsForGame((List<PersistentCard>) persistentGame.getCardsInField()));
        result.getUnusedCards().clear();
        result.setUnusedCards(persistentGame.getUnusedCards());
        session.flush();
        session.close();
        saveGame(result);
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