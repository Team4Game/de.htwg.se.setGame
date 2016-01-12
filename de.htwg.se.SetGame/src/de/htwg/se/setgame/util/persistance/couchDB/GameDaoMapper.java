package de.htwg.se.setgame.util.persistance.couchDB;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistance.couchDB.pojo.Game;

/**
 * Created by David on 04.01.16.
 */
public class GameDaoMapper {

    public static IGame getGame(IGame game){
        IGame result = new Game();
        result.setPlayerOne(game.getPlayerOne());
        result.setPlayerTwo(game.getPlayerTwo());
        result.setCardInField(game.getCardInField());
        result.setUnusedCards(game.getUnusedCards());
        result.setCardInField(game.getCardInField());
        result.setGameToken(game.getToken());
        return  result;
    }

    public static IGame update(Game toUpadate, IGame game) {
        IGame result = toUpadate;
        result.setPlayerOne(game.getPlayerOne());
        result.setPlayerTwo(game.getPlayerTwo());
        result.setCardInField(game.getCardInField());
        result.setUnusedCards(game.getUnusedCards());
        result.setCardInField(game.getCardInField());
        result.setGameToken(game.getToken());

        return  result;
    }
}
