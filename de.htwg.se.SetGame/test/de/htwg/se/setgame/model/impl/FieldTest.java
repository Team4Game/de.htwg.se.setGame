package de.htwg.se.setgame.model.impl;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.Field;

public class FieldTest {
	Field field;

	@Before
	public void setUp() {
		this.field = new Field();
	}


    @Test
    public void testStartUp_ok(){
        this.field.startUp();
        org.junit.Assert.assertTrue(this.field.getAllCardsInGame() != null);
        org.junit.Assert.assertEquals(this.field.getSizeofField(), this.field.getCardInFieldGame().size());
        /*initial value of field*/
        org.junit.Assert.assertTrue(this.field.getSizeofField() == this.field.INITIALVALUEOFFIELD);
    }

	@Test
	public void testRand() {
        this.field.startUp();
		TreeSet<Integer> liste = new TreeSet<Integer>();
		liste.addAll(this.field.getRamdomListe().values());
        org.junit.Assert.assertTrue(liste.size() == 81);
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
        org.junit.Assert.assertTrue(this.field.getAllCardsInGame().isEmpty());
	}

	@Test
	public void testCardsInField() {
        this.field.startUp();
        List<ICard> cardInField = this.field.getCardsInField();
        org.junit.Assert.assertTrue(this.field.getCardInFieldGame().values().containsAll(this.field.getCardsInField()));

	}
    @Test
    public void resetGame(){
        this.field.startUp();
        this.field.setSizeOfField(50);
        this.field.clear();
        org.junit.Assert.assertTrue(this.field.getAllCardsInGame() != null);
        org.junit.Assert.assertEquals(this.field.getSizeofField(), this.field.getCardInFieldGame().size());
        /*initial value of field*/
        org.junit.Assert.assertTrue(this.field.getSizeofField() == this.field.INITIALVALUEOFFIELD);
    }

	@Test
	public void testSetSizeOfField() {
        this.field.startUp();
		LinkedList<ICard> list = new LinkedList<ICard>();
		list.add(this.field.getCardsInField().get(0));
		list.add(this.field.getCardInFieldGame().get(1));
		list.add(this.field.getCardInFieldGame().get(2));
		this.field.setSizeOfField(15);
        org.junit.Assert.assertTrue(this.field.getCardsInField().size() == 15);
		this.field.setSizeOfField(12);
        org.junit.Assert.assertTrue(this.field.getCardsInField().size() == 12);
	}
 
	@Test
	public void testChangeCards() {
        this.field.startUp();
        List<ICard> liste =  new LinkedList<ICard>();
		liste.add(this.field.getCardInFieldGame().get(0));
		liste.add(this.field.getUnusedCards().get(0));
		liste.add(this.field.getUnusedCards().get(1));
		this.field.changeCards(liste);


	}

	@Test
	public void testGetPackForControler() {
        this.field.startUp();
		if(field.getAllCardsInGame().size() != 81){
			fail("feld gr��e stimmt nicht");
		}
	}

	@Test
	public void testGetSizeofField() {
        this.field.startUp();
		if(this.field.getSizeofField() != 12){
			fail("Feld an anfagen muss 12 sein!!!");
		}
	}
}