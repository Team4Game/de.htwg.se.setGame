package de.htwg.se.setgame.controller;

import de.htwg.se.setgame.controller.impl.ki.KILevel;

/**
 * Created by David on 11.11.15.
 */
public interface IPlugin {

    void startKi(KILevel level);

    void stopKi();

    boolean isKiRightLevel(KILevel level);

}
