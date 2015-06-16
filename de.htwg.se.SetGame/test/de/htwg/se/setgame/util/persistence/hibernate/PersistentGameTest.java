package de.htwg.se.setgame.util.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by raina on 16.06.2015.
 */
public class PersistentGameTest {
    PersistentGame target;
    String uid;

    @Before
    public void setUp() {
        PersistentPlayer playerOne = new PersistentPlayer();
        playerOne.setCounter(4);
        playerOne.setPid(1);
        PersistentPlayer playerTwo = new PersistentPlayer();
        playerTwo.setCounter(3);
        playerTwo.setPid(2);
        int counter = 4;
        Collection<PersistentCard> cardsInField = new ArrayList<PersistentCard>();
        PersistentCard cardfield = new PersistentCard();
        cardfield.setColor("red");
        cardfield.setNumberOfComponents(1);
        cardfield.setForm("wave");
        cardfield.setPanelFilling("fill");
        cardsInField.add(cardfield);
        PersistentCard card = new PersistentCard();
        card.setColor("green");
        card.setNumberOfComponents(1);
        card.setForm("wave");
        card.setPanelFilling("fill");
        Collection<PersistentCard> unusedCards = new ArrayList<PersistentCard>();
        unusedCards.add(card);

        // generate unique id
        this.uid = UUID.randomUUID().toString();

        this.target = new PersistentGame();
        this.target.setPlayerOne(playerOne);
        this.target.setPlayerTwo(playerTwo);
        this.target.setCardsInField(cardsInField);
        this.target.setUnusedCards(unusedCards);
        this.target.setCounter(counter);
        this.target.setGameID(uid);


    }

    @Test
    public void createPersistenceGame_ok() {
        Session session = SessionServiceHibernate.getSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            session.saveOrUpdate(target.getPlayerOne());
            session.saveOrUpdate(target.getPlayerTwo());
            for (PersistentCard card : target.getCardsInField()) {
                session.saveOrUpdate(card);
            }

            for (PersistentCard card : target.getUnusedCards()) {
                session.saveOrUpdate(card);
            }
            session.saveOrUpdate(target);

        } catch (HibernateException ex) {
            Assert.assertTrue(false);
            if (transaction != null) {
                System.out.println(ex.toString());
                transaction.rollback();
            }
        }
        Assert.assertTrue(target.getId() != null);
        Assert.assertTrue(target.getGameID().equals(uid));
        Assert.assertTrue(target.getPlayerOne() != null);
        Assert.assertTrue(target.getPlayerOne().getPlayerID() != null);
        Assert.assertTrue(target.getPlayerTwo() != null);
        Assert.assertTrue(target.getCardsInField() != null);
        Assert.assertTrue(target.getUnusedCards() != null);
        Assert.assertTrue(target.getCounter() == 4);

        transaction.rollback();
        session.close();

    }
}
