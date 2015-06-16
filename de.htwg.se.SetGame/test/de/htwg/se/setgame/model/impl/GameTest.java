package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by raina on 17.06.2015.
 */
public class GameTest {
    IGame target;
    IModelFactory modelFactory;
    @Before
    public void setUp(){
        this.modelFactory = new ModelFactory();
        this.target = modelFactory.createGame();


    }
    @Test
    public void setGame(){
        this.target.setId("id");
        this.target.setPlayerOne(modelFactory.createPlayer());
        this.target.setPlayerTwo(modelFactory.createPlayer());
        this.target.setCounter(5);
        this.target.setUnusedCards(new LinkedList<ICard>());
        this.target.setCardsInField(new HashMap<Integer, ICard>());
        Assert.assertTrue(null != target.getPlayerOne());
        Assert.assertTrue(null != target.getPlayerTwo());
        Assert.assertTrue(null != target.getCardsInField());
        Assert.assertTrue(null != target.getUnusedCards());
        Assert.assertTrue(null != target.getId());
        Assert.assertTrue(5 == target.getCounter());

    }

}
