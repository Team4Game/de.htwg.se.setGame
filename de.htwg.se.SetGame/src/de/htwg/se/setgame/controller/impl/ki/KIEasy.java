package de.htwg.se.setgame.controller.impl.ki;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKiPlugin;
import de.htwg.se.setgame.model.ICard;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by David on 17.06.15.
 */
public class KIEasy extends AKI {
    private static final int TIMEFOREASY = 20000;
    private static final String KIEASY = "Easy";

    public KIEasy() {
        super.timer = new Timer();
        super.startTimeTask();
    }

    @Override
    public boolean isKiLevel(String kiLevel) {
        return kiLevel.equals(KIEASY);
    }

    @Override
    public void initKI() {
        super.setWorking(true);
        super.initiationKI(TIMEFOREASY);

    }


}
