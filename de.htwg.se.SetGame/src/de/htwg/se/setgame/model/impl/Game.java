package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;

import java.util.List;

/**
 * Created by raina on 03.06.2015.
 */
public class Game implements IGame {

    @Override
    public void setPlayer(IPlayer playerOne, IPlayer playerTwo) {

    }

    @Override
    public void setCardInField(List<ICard> cardsInField) {

    }

    @Override
    public void setUnusedCards(List<ICard> unusedCards) {

    }

    @Override
    public void setGameToken(String token) {

    }

    @Override
    public IPlayer getPlayerOne() {
        return null;
    }

    @Override
    public IPlayer getPlayerTwo() {
        return null;
    }

    @Override
    public List<ICard> getCardInField() {
        return null;
    }

    @Override
    public List<ICard> getUnusedCards() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }
}
