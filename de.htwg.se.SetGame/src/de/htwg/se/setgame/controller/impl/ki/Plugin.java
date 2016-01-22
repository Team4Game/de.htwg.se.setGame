package de.htwg.se.setgame.controller.impl.ki;

import com.google.inject.Inject;
import de.htwg.se.setgame.controller.*;
import de.htwg.se.setgame.model.ICard;

import java.util.*;

/**
 * Created by David on 11.11.15.
 */
public abstract class Plugin implements IPlugin {


    @Inject
    private IController controller;

    public boolean isRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private boolean isRunning;
    private Timer timer = new Timer();
    private TimerTask timerTask; //how to change that??
    private static final int PLAYER = 2;
    private int timeToRun;

    public int getTimeToRun() {
        return timeToRun;
    }

    public void setTimeToRun(int timeToRun) {
        this.timeToRun = timeToRun;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void startKi(KILevel level) {


        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    List<ICard> solution = controller.getASetInGame();
                    controller.isASetForController(solution.get(0), solution.get(1), solution.get(2), PLAYER);
                }
            };
        }
        System.out.println(timeToRun);
        System.out.println("ki:" + level);
        System.out.println("Time to RUN " + isRunning);
        timer.scheduleAtFixedRate(timerTask, timeToRun, timeToRun);


    }

    protected void startTimeTask() {

    }

    @Override
    public void stopKi(KILevel level) {
        System.out.println("stop KI out Running: " + isRunning);
        System.out.println("ki" + level);
        if (isRunning) {
            System.out.println("stop timer for:" + timeToRun);
            timer.cancel();
            timerTask.cancel();
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    List<ICard> solution = controller.getASetInGame();
                    controller.isASetForController(solution.get(0), solution.get(1), solution.get(2), PLAYER);
                }
            };
            isRunning = false;

        }
    }


}
