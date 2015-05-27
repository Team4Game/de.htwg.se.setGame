package de.htwg.se.setgame.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;

public class CardTest {

	ICard target = null;
	@Before
	public void setUp() {
		this.target = new Card("red", "wave", "fill", 1);

	}
	@Test
	public void testCard() {
		assert(target != null);
		
	}
    @Test
    public void createCartTest_Fail(){
        Card c = new Card("bla","bla","bla", -100 );
        Assert.assertTrue(c.getColor() == null && c.getForm() == null && c.getPanelFilling() == null && c.getNumberOfComponents() == -1);

    }
    @Test
    public void createCart_ok(){
        Card card1 = new Card(Pack.COLORS[0], Pack.FORME[1], Pack.FILL[1], Pack.NUMBEROFCOMPONET[0]);
        Assert.assertTrue(card1.compareTo(target));
    }
    @Test
    public void test_ToString(){
        String result = target.toString();
        Assert.assertTrue(result != null);

    }

}
