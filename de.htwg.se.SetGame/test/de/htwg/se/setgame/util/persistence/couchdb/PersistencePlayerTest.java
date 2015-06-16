package de.htwg.se.setgame.util.persistence.couchdb;


import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.se.setgame.SetGameModule;
import de.htwg.se.setgame.model.IModelFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 16.06.2015.
 */
public class PersistencePlayerTest {
    private PersistentPlayer target;
    private GameDao gameDao;
    @Before
    public void setUp(){
        this.target = new PersistentPlayer();

        Injector injector = Guice.createInjector(new SetGameModule());
        IModelFactory modelFactory = injector.getInstance(IModelFactory.class);
        this.gameDao = new GameDao();
    }

    @Test
    public void getPersistentPlayer(){
        String uid = "b3192b4a-55ba-4adc-9047-764778fd89c9";
        PersistentGame game =(PersistentGame) gameDao.findGame(uid);
        this.target = (PersistentPlayer) game.getPlayerOne();
        Assert.assertTrue(null != game.getDbIdGame());
        Assert.assertTrue(null != game.getDbRevGame());
        target.setDbIdPlayer("ulala");
        target.setDbRevPlayer("tralala");

        Assert.assertTrue(target.getDbIdPlayer().equals("ulala"));
        Assert.assertTrue(target.getDbRevPlayer().equals("tralala"));
    }



}
