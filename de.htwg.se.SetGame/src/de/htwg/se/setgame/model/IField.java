package de.htwg.se.setgame.model;


import java.util.Map;
public interface IField {
    void setCardInField(Map<Integer,ICard> cardsInField);
    Map<Integer,ICard>  getCardsInField();

}