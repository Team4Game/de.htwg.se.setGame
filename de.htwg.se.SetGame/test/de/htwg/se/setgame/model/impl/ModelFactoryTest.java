package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 17.06.2015.
 */
public class ModelFactoryTest {
    IModelFactory target;

    @Before
    public void setUp(){
        this.target = new ModelFactory();
    }
    @Test
    public void createCard(){
        ICard result = target.createCard();

        Assert.assertTrue(result != null);
    }
    @Test
    public void createField(){
        IField result = target.createField();

        Assert.assertTrue(result != null);
    }
    @Test
    public void createGame(){
        IGame result = target.createGame();

        Assert.assertTrue(result != null);
    }
    @Test
    public void createPlayer(){
        IPlayer result = target.createPlayer();

        Assert.assertTrue(result != null);
    }
    @Test
    public void createPack(){
        IPack result = target.createPack();

        Assert.assertTrue(result != null);
    }
}
