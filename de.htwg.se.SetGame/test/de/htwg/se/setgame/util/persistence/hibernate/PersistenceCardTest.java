package de.htwg.se.setgame.util.persistence.hibernate;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.Card;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 16.06.2015.
 */
public class PersistenceCardTest {
    private PersistentCard target;
    private ICard card;

    @Before
    public void setUp() {
        this.target = new PersistentCard();
        this.target.setColor("red");
        this.target.setForm("wave");
        this.target.setPanelFilling("fill");
        this.target.setNumberOfComponents(2);
        this.card = new Card();
        this.card.setColor("red");
        this.card.setForm("wave");
        this.card.setPanelFilling("fill");
        this.card.setNumberOfComponents(2);
    }

    @Test
    public void createPersistenceCard_ok() {
        Session session = DaoManager.getSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            session.saveOrUpdate(target);

        } catch (HibernateException ex) {
            Assert.assertTrue(false);
            if (transaction != null) {
                System.out.println(ex.toString());
                transaction.rollback();
            }
        }

        Assert.assertTrue(target.getCardID() != null);
        Assert.assertTrue(target.getColor().equals("red"));
        Assert.assertTrue(target.getForm().equals("wave"));
        Assert.assertTrue(target.getPanelFilling().equals("fill"));
        Assert.assertTrue(target.getNumberOfComponents() == 2);
        Assert.assertTrue(target.compareTo(card));
        Assert.assertTrue(target.toString() != null);

        transaction.rollback();
        session.flush();
        session.close();

    }

    @Test
    public void createPersistenceCard_fail() {
        this.target.setColor("yellowSubmarino");
        this.target.setForm("uhla-uhla");
        this.target.setPanelFilling("fillBurNotFill");
        this.target.setNumberOfComponents(100);
        Session session = DaoManager.getSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            session.saveOrUpdate(target);

        } catch (HibernateException ex) {
            //it should give a exception!!!
            Assert.assertTrue(true);
        }

        Assert.assertTrue(target.getCardID() == null);
        Assert.assertTrue(target.getColor() == null);
        Assert.assertTrue(target.getForm() == null);
        Assert.assertTrue(target.getPanelFilling() == null);
        Assert.assertTrue(target.getNumberOfComponents() == -1);

        transaction.rollback();
        session.close();

    }
    @Test
    public void createPersistenceCardCompareTo_false() {
        this.target.setColor("red");
        this.target.setForm("balk");
        this.target.setPanelFilling("fill");
        this.target.setNumberOfComponents(2);
        Session session = DaoManager.getSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            session.saveOrUpdate(target);

        } catch (HibernateException ex) {
            Assert.assertTrue(false);
            if (transaction != null) {
                System.out.println(ex.toString());
                transaction.rollback();
            }
        }
        Assert.assertTrue(target.compareTo(card) == false);
        transaction.rollback();
        session.flush();
        session.close();

    }



}
