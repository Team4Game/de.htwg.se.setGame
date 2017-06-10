package de.htwg.se.setgame.aview.gui;

import de.htwg.se.setgame.controller.IKiPlugin;
import de.htwg.se.setgame.controller.impl.SetController;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.util.observer.Event;
import de.htwg.se.setgame.util.persistence.couchdb.GameDao;
import org.junit.Before;
import org.junit.Test;

import java.util.TreeSet;

/**
 * Created by raina on 17.06.2015.
 */
public class GUITest {
    private GUI target;

    @Before
    public void setUp() {
        target = new GUI((new SetController(new ModelFactory(), new GameDao(), new TreeSet<IKiPlugin>())));

    }

    @Test
    public void tryGui() {
        target.update(new Event() {
        });
    }
}
