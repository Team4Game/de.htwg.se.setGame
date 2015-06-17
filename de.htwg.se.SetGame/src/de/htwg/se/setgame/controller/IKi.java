package de.htwg.se.setgame.controller;

/**
 * Created by David on 12.06.15.
 */
public interface IKi {

    /**
     * method call set when time is over
     */
    void playSet();

    /**
     * method stop KI
     */
    void stopKI();

    /**
     * if gamer found a set KI should start from begin
     */
    void gamerFoundASet();


}
