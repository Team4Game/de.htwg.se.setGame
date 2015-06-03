package de.htwg.se.setgame.model;

import de.htwg.se.setgame.model.impl.Player;

import java.util.List;

/**
 * Created by raina on 03.06.2015.
 */
public interface IGame {
    void setPlayer(IPlayer playerOne,IPlayer playerTwo);
    void setCardInField(List<ICard> cardsInField);
    void setUnusedCards(List<ICard> unusedCards);
    void setGameToken(String token);
    IPlayer getPlayerOne();
    IPlayer getPlayerTwo();
    List<ICard> getCardInField();
    List<ICard> getUnusedCards();
    String getToken();



}
