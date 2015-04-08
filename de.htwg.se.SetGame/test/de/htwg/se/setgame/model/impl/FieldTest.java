package de.htwg.se.setgame.model.impl;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.Field;

public class FieldTest {
	Field field;

	@Before
	public void setUp() {
		this.field = new Field();
		this.field.startUp();
	}

	@Test
	public void test() {
		if(this.field == null){
			fail("field object ist gleich null");
			
		}
	}

	@Test
	public void testField() {
		if((this.field instanceof Field) == false){
			fail("field ist keine object der gr�sse field");
		}
	}

	@Test
	public void testsartUp() {
		System.out.println(this.field.getCardInFieldGame());

		if(this.field.getSizeofField() != 12){
			fail("feld is not the rigth size");
		}

	}

	@Test
	public void testRand() {
		TreeSet<Integer> liste = new TreeSet<Integer>();
		liste.addAll(this.field.getRamdomListe().values());
		if(liste.size() != 81){
			fail("wrong size of randomgeneretor");
		}

	}

	@Test
	public void testFoundSet() {
		LinkedList<ICard> liste = new LinkedList<ICard>();
		liste.addAll(field.getAllCardsInGame());
		for (int index = 0; index < (liste.size()-2); index++) {
			this.field.foundSet(liste.get(index),
					liste.get((index+1)),
					liste.get(index+2));
		}
		if(!this.field.getAllCardsInGame().isEmpty()){
			fail("found a set methode is not working as the way we wish :/");
		}
	}

	@Test
	public void testCardsInField() {
		if(!this.field.getCardInFieldGame().values().containsAll(this.field.getCardsInField())){
			fail("the card on feld are not the same as in the methode get card in field");
		}
	}

	@Test
	public void testSetSizeOfField() {
		LinkedList<ICard> list = new LinkedList<ICard>();
		list.add(this.field.getCardsInField().get(0));
		list.add(this.field.getCardInFieldGame().get(1));
		list.add(this.field.getCardInFieldGame().get(2));
		this.field.setSizeOfField(15);
		if(this.field.getCardsInField().size() != 15){
			fail("field size has been not modifed");
		}
		this.field.setSizeOfField(12);
		if(this.field.getSizeofField() != 12){
			fail("field size is wrong :(");
		}
	}
 
	@Test
	public void testChangeCards() {
		List<ICard> liste =  new LinkedList<ICard>(); 
		
		liste.add(this.field.getCardInFieldGame().get(0));
		liste.add(this.field.getUnusedCards().get(0));
		liste.add(this.field.getUnusedCards().get(1));
		 this.field.changeCards(liste);
		
	}

	@Test
	public void testGetPackForControler() {
		if(field.getAllCardsInGame().size() != 81){
			fail("feld gr��e stimmt nicht");
		}
	}

	@Test
	public void testGetSizeofField() {
		if(this.field.getSizeofField() != 12){
			fail("Feld an anfagen muss 12 sein!!!");
		}
	}
}