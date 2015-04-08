package de.htwg.se.setgame.model.impl;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.Card;

public class CardTest {

	ICard card = null;
	@Before
	public void setUp() {
		this.card = new Card("red", "wave", "fill", 1);

	}
	@Test
	public void testCard() {
		assert(card != null);
		
	}

	@Test
	public void testGetColor() {
		assert(card.getColor() != null);
	}
	@Test
	public void testGetFomr() {
		assert(card.getForm() != null);
	}

	@Test
	public void testGetPanelFilling() {
		assert(card.getPanelFilling() != null);
	}

	@Test
	public void testGetNumberOfComponents() {
		assert(card.getNumberOfComponents() != -1);
	}

}
