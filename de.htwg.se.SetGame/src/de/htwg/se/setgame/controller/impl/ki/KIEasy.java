package de.htwg.se.setgame.controller.impl.ki;

import com.google.inject.Inject;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKiPlugin;
import de.htwg.se.setgame.model.ICard;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by David on 17.06.15.
 */
public class KIEasy implements IKiPlugin {
    public IController getController() {
        return controller;
    }

    public void setController(IController controller) {
        this.controller = controller;
    }

    private IController controller;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    private int player;
    private Timer timer = new Timer();
    private TimerTask timertask;
    private static final int TIME = 1000;

    public KIEasy(){

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
