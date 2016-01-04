package de.htwg.se.setgame.util.persistance.hibernate;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;

import java.util.List;
import java.util.Map;

/**
 * Created by David on 04.01.16.
 */
public class Game implements IGame {
    @Override
    public void setPlayerOne(IPlayer player1) {

    }

    @Override
    public void setPlayerTwo(IPlayer player2) {

    }

    @Override
    public void setCardInField(Map<Integer, ICard> cardsInField) {

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
    public Map<Integer, ICard> getCardInField() {
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
