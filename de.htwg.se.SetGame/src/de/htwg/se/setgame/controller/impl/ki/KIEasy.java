package de.htwg.se.setgame.controller.impl.ki;

import java.util.Timer;

/**
 * Created by David on 17.06.15.
 */
public class KIEasy extends AKI {

    private static final int TIMEFOREASY = 20000;
    private static final String KIEASY = "Easy";

    public KIEasy() {
        super.setTimer(new Timer());
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
