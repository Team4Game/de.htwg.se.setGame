package de.htwg.se.setgame.controller.impl.ki;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKiPlugin;
import de.htwg.se.setgame.model.ICard;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by raina on 18.06.2015.
 */
public abstract class AKI implements IKiPlugin {
    private static final int PLAYER = 2;

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    private Timer timer;
    private IController controller;
    private TimerTask timertask;
    private boolean isWorking;

    protected boolean isWorking() {
        return isWorking;
    }

    protected void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public IController getController() {
        return controller;
    }

    @Override
    public void setController(IController controller) {
        this.controller = controller;
    }



    protected void startTimeTask() {
        timertask = new TimerTask() {
            @Override
            public void run() {
                playSet();
            }
        };
    }

    @Override
    public void playSet() {
        List<ICard> solution = controller.getSetInField();
        if (solution != null) {
            controller.isASetForController(solution.get(0), solution.get(1), solution.get(2), PLAYER);

        }
    }

    @Override
    public void gamerFoundASet() {
        cancelTask();
        initKI();


    }

    @Override
    public void stopKI() {
        if (isWorking) {
            cancelTask();
            isWorking = false;
        }
    }

    private void cancelTask() {
        timertask.cancel();
        timer.cancel();
        timer = new Timer();
        startTimeTask();
    }

    public void initiationKI(int time){
        timer.scheduleAtFixedRate(timertask, time, time);
    }
}
