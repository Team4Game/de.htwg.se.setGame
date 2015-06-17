package de.htwg.se.setgame.controller.impl.ki;

import com.google.inject.Inject;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKi;
import de.htwg.se.setgame.model.ICard;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by David on 17.06.15.
 */
public class KIEasy implements IKi{
    private IController controller;
    private int player;
    private Timer timer = new Timer();
    private boolean foundSet = false;
    private TimerTask timertask;
    private static final int TIME = 1000;

    @Inject
    public KIEasy(IController controller, int player){
        this.controller = controller;
    }
    public void initiatKI(){

        timertask = new TimerTask() {
            @Override
            public void run() {
                playSet();
            }
        };
        timer.schedule(timertask, TIME);

    }

    @Override
    public void playSet() {
        List<ICard> solution = controller.getASetInGame();
        controller.isASetForController(solution.get(0),solution.get(1),solution.get(2),player);
    }

    @Override
    public void gamerFoundASet() {
        timer.cancel();
        timer.schedule(timertask, TIME);

    }

    @Override
    public void stopKI() {
        timer.cancel();
    }

}
