package de.htwg.se.setgame.model;

import java.util.List;

/**
 * Created by philipp on 10.01.15.
 */
public interface IPack {
    /**
     *
     * @return pack
     */
    List<ICard> getPack();

    /**
     *
     * @param pack for game
     */
    void setPack(List<ICard> pack);



}
