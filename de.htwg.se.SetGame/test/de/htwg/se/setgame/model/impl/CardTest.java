package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardTest {

	ICard target = null;
	@Before
	public void setUp() {
        IModelFactory modelFactory = new ModelFactory();
        this.target = modelFactory.createCard();
        target.setColor("red");
        target.setForm("wave");
        target.setPanelFilling("fill");
        target.setNumberOfComponents(1);

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
        Card card1 = new Card();
        card1.setColor(CardAtributen.COLORS[0]);
        card1.setForm(CardAtributen.FORME[1]);
        card1.setPanelFilling(CardAtributen.FILL[1]);
        card1.setNumberOfComponents(CardAtributen.NUMBEROFCOMPONET[0]);
        Assert.assertTrue(card1.compareTo(target));
    }
    @Test
    public void test_ToString(){
        String result = target.toString();
        Assert.assertTrue(result != null);

    }

}
