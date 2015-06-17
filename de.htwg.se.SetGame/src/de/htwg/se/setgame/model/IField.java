package de.htwg.se.setgame.model;


import java.util.Map;
public interface IField {
    /**
     *
     * @param cardsInField all the cards that are showed in game
     */
    void setCardInField(Map<Integer,ICard> cardsInField);

    /**
     *
     * @return cards in field
     */
    Map<Integer,ICard>  getCardsInField();

}