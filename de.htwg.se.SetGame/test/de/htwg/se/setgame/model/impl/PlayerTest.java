package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 17.06.2015.
 */
public class PlayerTest {
    IPlayer target;

    @Before
    public void setUp(){
        IModelFactory modelFactory = new ModelFactory();
        target = modelFactory.createPlayer();
    }

    @Test
    public void setPlayer(){
        target.setCounter(3);
        target.setPid(1);
        Assert.assertTrue(3 == target.getCounter());
        Assert.assertTrue(1 == target.getPid());
    }
}
