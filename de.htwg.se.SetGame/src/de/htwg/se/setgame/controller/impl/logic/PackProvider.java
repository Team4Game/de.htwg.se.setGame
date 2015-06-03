package de.htwg.se.setgame.controller.impl.logic;

import de.htwg.se.setgame.model.impl.Card;

import java.util.List;

/**
 * Created by raina on 01.05.2015.
 */
public interface PackProvider {

    void generateCards();
    List<Card> getCardInField();
    List<Card> getUnsedCards();

}
