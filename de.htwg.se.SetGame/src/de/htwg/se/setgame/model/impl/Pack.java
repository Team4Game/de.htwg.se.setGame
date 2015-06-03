package de.htwg.se.setgame.model.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IPack;

/**
 * @author David Simon & Raina Bertolini class use to create all the
 *         combinations of the pack
 */
public class Pack implements IPack {

    List<ICard> unUsedCards;
    public Pack(){
        this.unUsedCards = new LinkedList<ICard>();

    }
    @Override
    public List<ICard> getPack() {
        return unUsedCards;
    }

    @Override
    public void setPack(List<ICard> pack) {
        this.unUsedCards = pack;
    }
}
