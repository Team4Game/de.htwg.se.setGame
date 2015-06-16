package de.htwg.se.setgame.util.persistence.couchdb;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 16.06.2015.
 */
public class PersistenceGameTest {
    private PersistentGame target;
    private GameDao gameDao;
    @Before
    public void setUp(){
        this.target = new PersistentGame();
        this.gameDao = new GameDao();
    }

    @Test
    public void getPersistentGame(){
        String uid = "b3192b4a-55ba-4adc-9047-764778fd89c9";
        PersistentGame game =(PersistentGame) gameDao.findGame(uid);
        Assert.assertTrue(null != game.getDbId());
        Assert.assertTrue(null != game.getDbRev());
        game.setDbId("ulala");
        game.setDbRev("tralala");

        Assert.assertTrue(game.getDbId().equals("ulala"));
        Assert.assertTrue(game.getDbRev().equals("tralala"));
    }



}
