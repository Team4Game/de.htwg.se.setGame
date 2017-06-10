package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPack;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PackTest {
	IPack target;

    IModelFactory modelFactory;
    List<ICard> list;
    @Before
    public void setUp() {
        modelFactory = new ModelFactory();
        this.target = modelFactory.createPack();
        this.list = new LinkedList<ICard>();
        ICard card = modelFactory.createCard();
        card.setColor("red");
        card.setForm("wave");
        card.setNumberOfComponents(1);
        card.setPanelFilling("fill");
        list.add(card);
    }
    @Test
    public void setPack(){
        target.setPack(list);
        Assert.assertTrue(target.getPack().size() != 0);

    }
    @Test
    public void getPack(){
        target.setPack(new LinkedList<ICard>());
        Assert.assertTrue(target.getPack().size() == 0);

    }
}
