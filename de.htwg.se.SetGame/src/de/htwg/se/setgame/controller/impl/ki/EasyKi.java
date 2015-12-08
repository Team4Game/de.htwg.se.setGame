package de.htwg.se.setgame.controller.impl.ki;

/**
 * Created by David on 11.11.15.
 */
public class EasyKi extends Plugin {
    private KILevel mylevel = KILevel.EASY;

    @Override
    public boolean isKiRightLevel(KILevel level) {
        if(level == mylevel) {
            super.setTimeToRun(15000);
            super.setIsRunning(true);
            return level == mylevel;
        }
        return false;
    }



}
