package de.htwg.se.setgame.model.impl;



import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;


public class FieldTest {
	Field field;

	@Before
	public void setUp() {
		this.field = new Field(new ModelFactory().createPack(), 12);
	}


    @Test
    public void testStartUp_ok(){
        this.field.startUp();
        Assert.assertTrue(this.field.getAllCardsInGame() != null);
        Assert.assertEquals(this.field.getSizeofField(), this.field.getCardInFieldGame().size());
        /*initial value of field*/
        Assert.assertTrue(this.field.getSizeofField() == this.field.INITIALVALUEOFFIELD);
        Assert.assertTrue(field.getAllCardsInGame().size() == 81);
    }

	@Test
	public void testRand() {
        this.field.startUp();
		TreeSet<Integer> liste = new TreeSet<Integer>();
		liste.addAll(this.field.getRamdomListe().values());
        Assert.assertTrue(liste.size() == 81);
	}

	@Test
	public void testFoundSet() {
        this.field.startUp();
		LinkedList<ICard> liste = new LinkedList<ICard>();
		liste.addAll(field.getAllCardsInGame());
		for (int index = 0; index < (liste.size()-2); index++) {
			this.field.foundSet(liste.get(index),
					liste.get((index+1)),
					liste.get(index+2));
		}
        Assert.assertTrue(this.field.getAllCardsInGame().isEmpty());
	}

	@Test
	public void testCardsInField() {
        this.field.startUp();
        List<ICard> cardInField = this.field.getCardsInField();
        Assert.assertTrue(this.field.getCardInFieldGame().values().containsAll(this.field.getCardsInField()));

	}
    @Test
    public void resetGame(){
        this.field.startUp();
        this.field.setSizeOfField(50);
        this.field.clear();
        Assert.assertTrue(this.field.getAllCardsInGame() != null);
        Assert.assertEquals(this.field.getSizeofField(), this.field.getCardInFieldGame().size());
        /*initial value of field*/
        Assert.assertTrue(this.field.getSizeofField() == this.field.INITIALVALUEOFFIELD);
    }

	@Test
	public void testSetSizeOfField() {
        this.field.startUp();
		this.field.setSizeOfField(15);
        Assert.assertTrue(this.field.getCardsInField().size() == 15);
		this.field.setSizeOfField(12);
        Assert.assertTrue(this.field.getCardsInField().size() == 12);
	}
 
	@Test
	public void testChangeCards() {
        this.field.startUp();
        List<ICard> liste =  new LinkedList<ICard>();
		liste.add(this.field.getCardInFieldGame().get(0));
		liste.add(this.field.getUnusedCards().get(0));
		liste.add(this.field.getUnusedCards().get(1));
		this.field.changeCards(liste);
        Assert.assertTrue(this.field.getCardInFieldGame().values().containsAll(liste));


	}
    @Test
    public void testTostring(){
        this.field.startUp();
       String field =  this.field.toString();
        Assert.assertTrue(field != null);
    }
}