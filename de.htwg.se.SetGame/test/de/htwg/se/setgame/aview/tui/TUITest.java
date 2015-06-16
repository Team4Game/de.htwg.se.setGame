package de.htwg.se.setgame.aview.tui;

import de.htwg.se.setgame.aview.tui.TextUI;
import de.htwg.se.setgame.controller.impl.ControllerDummy;
import de.htwg.se.setgame.controller.impl.SetController;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.util.persistence.couchdb.GameDao;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by raina on 17.06.2015.
 */
public class TUITest {
    private TextUI target;
    @Before
    public void setUp(){
        target = new TextUI(new SetController(new ModelFactory(), new GameDao(new ModelFactory())));

    }
    @Test
    public void toPrint(){
        target.printTUI();
        target.processInputLine("s");
    }
}
