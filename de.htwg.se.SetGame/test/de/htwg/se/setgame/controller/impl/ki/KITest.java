package de.htwg.se.setgame.controller.impl.ki;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.impl.SetController;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.util.persistence.couchdb.GameDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by David on 17.06.15.
 */
public class KITest {
    private KIEasy target;
    private IController controller;
    private int kiPlayer = 1;
    private int userPlayerNumber = 2;

    @Before
    public void setUp(){
        controller = new SetController(new ModelFactory(), new GameDao());
        this.target = new KIEasy(controller, kiPlayer);
    }
    @Test
    public void checkIfKiWorks(){
        target.initiatKI();
        int size = 81;
        while(size!= 78){
            size = controller.getCardinGame().size();
            if(size != 81){
                break;
            }

        }
        Assert.assertTrue( controller.getCardinGame().size() == 78);
        Assert.assertTrue( controller.getPlayerOnePoints() == 1);


    }
}
