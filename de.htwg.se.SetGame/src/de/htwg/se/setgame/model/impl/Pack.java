package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IPack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author David Simon & Raina Bertolini class use to create all the
 *         combinations of the pack
 */
public class Pack implements IPack {

    private LinkedList<ICard> unUsedCards;

    public Pack(){
        this.unUsedCards = new LinkedList<ICard>();

    }
    @Override
    public List<ICard> getPack() {
        return unUsedCards;
    }

    @Override
    public void setPack(List<ICard> pack) {
        this.unUsedCards.addAll(pack);
    }




}
