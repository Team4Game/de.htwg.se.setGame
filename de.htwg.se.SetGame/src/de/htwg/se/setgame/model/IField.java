package de.htwg.se.setgame.model;


import java.util.List;

public interface IField {
    void setCardInField(List<ICard> cardsInField);
    List<ICard> getCardsInField();

}