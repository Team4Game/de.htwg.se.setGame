package de.htwg.se.setgame.model.impl;
import org.junit.Assert;
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
    public void createCartTest_Fail(){
        Card c = new Card("bla","bla","bla", -100 );
        Assert.assertTrue(c.getColor() == null && c.getForm() == null && c.getPanelFilling() == null && c.getNumberOfComponents() == -1);

    }

	@Test
	public void testGetColor() {
		assert(card.getColor() != null);
	}
	@Test
	public void testGetForm() {
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
