package de.htwg.se.setgame.model;

import java.util.List;

/**
 * Created by philipp on 10.01.15.
 */
public interface IPack {
    List<ICard> getPack();
    void setPack(List<ICard> pack);
}
