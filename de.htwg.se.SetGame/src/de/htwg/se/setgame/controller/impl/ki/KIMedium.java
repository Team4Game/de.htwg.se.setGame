package de.htwg.se.setgame.controller.impl.ki;

import java.util.Timer;

/**
 * Created by raina on 18.06.2015.
 */
public class KIMedium extends AKI {
    private static final int TIMEMEDIUM = 10000;
    private static final String KIMEDIUM = "Medium";

    public KIMedium() {
        super.setTimer(new Timer());
        super.startTimeTask();
    }

    @Override
    public boolean isKiLevel(String kiLevel) {
        return kiLevel.equals(KIMEDIUM);
    }

    @Override
    public void initKI() {
        super.setWorking(true);
        super.initiationKI(TIMEMEDIUM);

    }
}
