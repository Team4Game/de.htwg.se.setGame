package de.htwg.se.setgame.util.persistance.hibernate;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.util.persistance.IGameDao;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.Card;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.CardsInField;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.Game;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.Player;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


/**
 * Created by David on 04.01.16.
 */
public class GameDao implements IGameDao {


    private static SessionFactory sessionFactory;

    private static Session getSession() {
        if (sessionFactory == null) {
            sessionFactory = new AnnotationConfiguration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
        }
        Session s = sessionFactory.openSession();
        return s;
    }

    @Override
    public boolean createOrUpdate(IGame gameObj) {
        boolean isDelete;
        if (findGame(gameObj.getToken()) != null) {
            isDelete = deleteGame(gameObj);

        }
        Game game = GameDaoMapper.getGame(gameObj);
        game.setCardsInFieldTable(createCardsInField(game));
        Player playerOne = savePlayer(game.getPlayerOne());
        game.setPlayerOne(playerOne);
        Player playerTwo = savePlayer(game.getPlayerTwo());
        ((Player) game.getPlayerOne()).setPlayerID(playerOne.getPlayerID());
        game.setPlayerTwo(playerTwo);
        ((Player) game.getPlayerTwo()).setPlayerID(playerTwo.getPlayerID());
        saveCards(game);
        saveGame(game);

        if (findGame(gameObj.getToken()) != null) {
            return true;
        }
        return false;
    }

    private CardsInField createCardsInField(Game game) {
        CardsInField cardsInField = new CardsInField();
        cardsInField.setCardsInField(new TreeMap<Integer, Card>());
        saveCardsF(game);
        cardsInField.getCardsInField().putAll(game.getCardsInFieldTable().getCardsInField());
        Transaction transaction = null;
        Session session = getSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(cardsInField);
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.flush();
        transaction.commit();
        session.close();
        game.setCardsInFieldTable(cardsInField);
        return cardsInField;
    }

    private void saveCards(Game game) {

        for (int i = 0; i < game.getUnusedCards().size(); i++) {
            Card c = saveCard(GameDaoMapper.getCard(game.getUnusedCards().get(i)));
            ((Card) game.getUnusedCards().get(i)).setCardID(c.getCardID());

        }
    }

    private Player savePlayer(IPlayer playerOne) {
        Transaction transaction = null;
        Session session = getSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(playerOne);
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.flush();
        transaction.commit();
        session.close();
        return (Player) playerOne;
    }

    private void saveCardsF(Game game) {

        for (Integer key : game.getCardsInFieldTable().getCardsInField().keySet()) {
            Card c = saveCard(GameDaoMapper.getCard(game.getCardsInFieldTable().getCardsInField().get(key)));
            ((Card) game.getCardsInFieldTable().getCardsInField().get(key)).setCardID(c.getCardID());
        }


    }

    private Card saveCard(Card card) {
        Transaction transaction = null;
        Session session = getSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(card);
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.flush();
        transaction.commit();
        session.close();
        return (Card) card;

    }

    private void saveGame(Game game) {
        Transaction transaction = null;
        Session session = getSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(game);
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.flush();
        transaction.commit();
        session.close();

    }

    //WICHTIG!
    private boolean deleteGame(IGame game) {
        Session session = getSession();
        Game result = (Game) session.createCriteria(Game.class).add(Restrictions.eq("token", game.getToken())).uniqueResult();
        session.close();

        if (result != null) {
            List<ICard> unusedCards = result.getUnusedCards();
            List<Card> cardsInField = new LinkedList<Card>();
            cardsInField.addAll(result.getCardsInFieldTable().getCardsInField().values());
            CardsInField cardsInField1 = result.getCardsInFieldTable();
            Player playerOne = (Player) result.getPlayerOne();
            Player playerTwo = (Player) result.getPlayerTwo();
            deleteGameTable(result);
            deletePlayer(playerOne);
            deletePlayer(playerTwo);
            deleteCardinFieldTable(cardsInField1);
            deleteCards(unusedCards);
            deleteCardsInField(cardsInField);


        }
        return true;
    }

    private void deletePlayer(Player player) {
        Session sessionDel = getSession();
        Transaction transaction = null;

        transaction = sessionDel.beginTransaction();

        sessionDel.delete(player);
        sessionDel.flush();
        transaction.commit();
        sessionDel.close();

    }

    private void deleteGameTable(Game result) {
        Session sessionDel = getSession();
        Transaction transaction = null;

        transaction = sessionDel.beginTransaction();

        sessionDel.delete(result);
        sessionDel.flush();
        transaction.commit();
        sessionDel.close();

    }

    private void deleteCardinFieldTable(CardsInField result) {

        Session sessionDel = getSession();
        Transaction transaction = null;

        transaction = sessionDel.beginTransaction();

        sessionDel.delete(result);
        sessionDel.flush();
        transaction.commit();
        sessionDel.close();

    }

    private void deleteCardsInField(List<Card> result) {
        for (Card card : result) {
            deleteCard(card);
        }
    }

    private void deleteCards(List<ICard> result) {
        for (ICard card : result) {
            deleteCard((Card) card);
        }
    }

    private void deleteCard(Card card) {
        Session sessionDel = getSession();
        Transaction transaction = null;

        transaction = sessionDel.beginTransaction();

        sessionDel.delete(card);
        sessionDel.flush();
        transaction.commit();
        sessionDel.close();
    }

    @Override
    public IGame findGame(String id) {
        IGame result = null;
        Session session = null;
        try {
            session = getSession();
            result = (Game) session.createCriteria(Game.class).add(Restrictions.eq("token", id)).uniqueResult();

            session.flush();
            session.close();
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public void closeDb() {
        getSession().close();
    }
}
