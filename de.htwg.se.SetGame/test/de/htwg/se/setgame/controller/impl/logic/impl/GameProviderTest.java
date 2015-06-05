package de.htwg.se.setgame.controller.impl.logic.impl;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.ModelFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by raina on 03.06.2015.
 */
public class GameProviderTest {


    GameProvider target;

    @Before
    public void setUp() {
        this.target = new GameProvider(new ModelFactory(), 12);
    }


    @Test
    public void testStartUp_ok() {
        this.target.startUp();
        Assert.assertTrue(this.target.getAllCardsInGame() != null);
        Assert.assertEquals(this.target.getSizeofField(), this.target.getCardInFieldGame().size());
        /*initial value of field*/
        Assert.assertTrue(this.target.getSizeofField() == this.target.INITIALVALUEOFFIELD);
        Assert.assertTrue(target.getAllCardsInGame().size() == 81);
    }

    @Test
    public void testRand() {
        this.target.startUp();
        TreeSet<Integer> liste = new TreeSet<Integer>();
        liste.addAll(this.target.getRamdomListe().values());
        Assert.assertTrue(liste.size() == 81);
    }

    @Test
    public void testFoundSet() {
        this.target.startUp();
        LinkedList<ICard> liste = new LinkedList<ICard>();
        liste.addAll(target.getAllCardsInGame());
        for (int index = 0; index < (liste.size() - 2); index++) {
            this.target.foundSet(liste.get(index),
                    liste.get((index + 1)),
                    liste.get(index + 2));
        }
        Assert.assertTrue(this.target.getAllCardsInGame().isEmpty());
    }

    @Test
    public void testCardsInField() {
        this.target.startUp();
        List<ICard> cardInField = this.target.getCardsInField();
        Assert.assertTrue(this.target.getCardInFieldGame().values().containsAll(this.target.getCardsInField()));

    }

    @Test
    public void resetGame() {
        this.target.startUp();
        this.target.setSizeOfField(50);
        this.target.clear();
        Assert.assertTrue(this.target.getAllCardsInGame() != null);
        Assert.assertEquals(this.target.getSizeofField(), this.target.getCardInFieldGame().size());
        /*initial value of field*/
        Assert.assertTrue(this.target.getSizeofField() == this.target.INITIALVALUEOFFIELD);
    }

    @Test
    public void testSetSizeOfField() {
        this.target.startUp();
        this.target.setSizeOfField(15);
        Assert.assertTrue(this.target.getCardsInField().size() == 15);
        this.target.setSizeOfField(12);
        Assert.assertTrue(this.target.getCardsInField().size() == 12);
    }

    @Test
    public void testChangeCards() {
        this.target.startUp();
        List<ICard> liste = new LinkedList<ICard>();
        liste.add(this.target.getCardInFieldGame().get(0));
        liste.add(this.target.getUnusedCards().get(0));
        liste.add(this.target.getUnusedCards().get(1));
        this.target.changeCards(liste);
        Assert.assertTrue(this.target.getCardInFieldGame().values().containsAll(liste));


    }

/*    @Test
    public void testTostring() {
        this.target.startUp();
        String field = this.target.toString();
        Assert.assertTrue(field != null);
    }*/
}


