package de.htwg.se.setgame.controller.impl.ki;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.se.setgame.SetGameModule;
import de.htwg.se.setgame.controller.IController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 18.06.2015.
 */
public class TestKIMedium {
    private KIMedium target;
    private IController controller;

    @Before
    public void setUp() {

        Injector injector = Guice.createInjector(new SetGameModule());
        controller = injector.getInstance(IController.class);
        this.target = new KIMedium();
        target.setController(controller);
    }

    @Test
    public void getLevel() {
        Assert.assertTrue(target.isKiLevel("Medium"));

    }

    @Test
    public void testKi() throws InterruptedException {
        target.initKI();
        Thread.sleep(10000);
        Assert.assertTrue(81 == controller.getCardinGame().size());
        Thread.sleep(6000);
        Assert.assertTrue(78 == controller.getCardinGame().size());
        target.stopKI();
    }
}
