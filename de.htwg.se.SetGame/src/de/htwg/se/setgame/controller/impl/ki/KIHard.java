package de.htwg.se.setgame.controller.impl.ki;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKiPlugin;
import de.htwg.se.setgame.model.ICard;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by raina on 17.06.2015.
 */
public class KIHard implements IKiPlugin {
    private static final String KILEVEL = "Hard";
    private static final int TIME = 5000;
    private static final int PLAYER = 2;
    private IController controller;
    private Timer timer;
    private TimerTask timertask;
    private boolean isWorking;

    public KIHard() {
        System.out.println("start ki");
        this.timer = new Timer();
        startTimeTask();
    }

    public IController getController() {
        return controller;
    }

    @Override
    public void setController(IController controller) {
        this.controller = controller;
    }

    @Override
    public void initKI() {
        isWorking = true;
        timer.scheduleAtFixedRate(timertask, TIME, TIME);

    }

    @Override
    public boolean isKiLevel(String kiLevel) {
        return kiLevel.equals(KILEVEL);
    }

    private void startTimeTask() {
        timertask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("running");
                System.out.println("Run Ki SEt");
                playSet();
            }
        };
    }

    @Override
    public void playSet() {
        System.out.println("ki found set");
        List<ICard> solution = controller.getSetInField();
        if (solution != null)
            controller.isASetForController(solution.get(0), solution.get(1), solution.get(2), PLAYER);
    }

    @Override
    public void gamerFoundASet() {
        System.out.println("gamer found set");
        cancelTask();
        initKI();


    }

    @Override
    public void stopKI() {
        System.out.println("cancel ki");
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

}
