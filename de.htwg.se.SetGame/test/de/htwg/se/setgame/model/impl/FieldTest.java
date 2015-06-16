package de.htwg.se.setgame.model.impl;


import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class FieldTest {
	IField target;
    IModelFactory modelFactory;
    Map<Integer, ICard> list;

    @Before
    public void setUp() {
        modelFactory = new ModelFactory();
		this.target = modelFactory.createField();
        this.list = new HashMap<Integer, ICard>();
        ICard card = modelFactory.createCard();
        card.setColor("red");
        card.setForm("wave");
        card.setNumberOfComponents(1);
        card.setPanelFilling("fill");

        list.put(0, card);
        for(int i = 1 ; i < 5 ; i++){
            list.put(i,createCard());
        }
    }
    private ICard createCard(){
        Card card1 = new Card();
        card1.setColor(CardAtributen.COLORS[2]);
        card1.setForm(CardAtributen.FORME[1]);
        card1.setPanelFilling(CardAtributen.FILL[1]);
        return card1;
    }
    @Test
    public void setFieldCards(){
        target.setCardInField(list);
        Assert.assertTrue(target.getCardsInField().size() != 0);

    }

    @Test
    public void getFieldCards() {
        Map<Integer, ICard> empty = new HashMap<Integer, ICard>();
        target.setCardInField(empty);
        Assert.assertTrue(target.getCardsInField().size() == 0);

    }

    @Test
    public void testToString() {
        target.setCardInField(list);
        String result = target.toString();
        Assert.assertTrue(result != null);
    }


}