package de.htwg.se.setgame.controller.impl.ki;

import java.util.Timer;

/**
 * Created by raina on 17.06.2015.
 */
public class KIHard extends AKI {
    private static final String KILEVEL = "Hard";
    private static final int TIME = 5000;
    public KIHard() {
        super.startTimeTask();
        super.setTimer(new Timer());
    }
    @Override
    public void initKI() {
        super.initiationKI(TIME);
        super.setWorking(true);


    }

    @Override
    public boolean isKiLevel(String kiLevel) {
        return kiLevel.equals(KILEVEL);
    }


}
