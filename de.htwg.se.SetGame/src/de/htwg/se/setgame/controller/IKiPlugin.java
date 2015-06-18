package de.htwg.se.setgame.controller;

/**
 * Created by David on 12.06.15.
 */
public interface IKiPlugin {

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

    /**
     * start ki
     */
    void initKI();

    /**
     *
     * @param kiLevel ex. Easy Hard
     * @return if Ki supports lever
     */
    boolean isKiLevel(String kiLevel);

    /**
     *
     * @param controller set controller for ki so that ki can play
     */
    void setController(IController controller);

}
