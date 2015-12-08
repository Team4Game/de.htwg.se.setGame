package de.htwg.se.setgame.controller.impl.ki;

/**
 * Created by David on 11.11.15.
 */
public class MediumKi extends Plugin {
    private KILevel mylevel = KILevel.MEDIUM;

    @Override
    public boolean isKiRightLevel(KILevel level) {
        if(level == mylevel) {
            super.setTimeToRun(10000);
            super.setIsRunning(true);
            return level == mylevel;
        }
        return false;
    }



}
