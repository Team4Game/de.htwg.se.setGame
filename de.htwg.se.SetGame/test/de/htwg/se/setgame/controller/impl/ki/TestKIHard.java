package de.htwg.se.setgame.controller.impl.ki;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.se.setgame.SetGameModule;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.model.ICard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by raina on 18.06.2015.
 */
public class TestKIHard {
    private KIHard target;
    private IController controller;

    @Before
    public void setUp() {

        Injector injector = Guice.createInjector(new SetGameModule());
        controller = injector.getInstance(IController.class);
        this.target = new KIHard();
        target.setController(controller);
    }

    @Test
    public void getLevel() {
        Assert.assertTrue(target.isKiLevel("Hard"));

    }

    @Test
    public void testKi() throws InterruptedException {
        target.initKI();
        Thread.sleep(4000);
        Assert.assertTrue(81 == controller.getCardinGame().size());
        Thread.sleep(2000);
        Assert.assertTrue(78 == controller.getCardinGame().size());
        target.stopKI();
    }
    @Test
    public void foundASet() throws InterruptedException {
        target.initKI();
        Thread.sleep(3000);
        target.gamerFoundASet();
        Assert.assertTrue(81 == controller.getCardinGame().size());
        Thread.sleep(3000);
        Assert.assertTrue(81 == controller.getCardinGame().size());
        Thread.sleep(3000);
        Assert.assertTrue(78 == controller.getCardinGame().size());

        target.stopKI();
    }
}
