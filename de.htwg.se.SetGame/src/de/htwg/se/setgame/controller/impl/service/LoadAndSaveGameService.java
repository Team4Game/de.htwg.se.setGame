package de.htwg.se.setgame.controller.impl.service;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.util.persistence.IGameDao;
import de.htwg.se.setgame.util.persistence.db4o.GameDao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by raina on 17.06.2015.
 */
public class LoadAndSaveGameService {
    private IGameDao gameDao;
    IModelFactory modelFactory;
    public LoadAndSaveGameService(IGameDao gameDao, IModelFactory modelFactory) {
        this.gameDao = gameDao;
        this.modelFactory = modelFactory;
    }

    /**
     *
     * @param uid
     * @param playerOneCounter
     * @param playerTwoCounter
     * @param counter
     * @param unused
     * @param cardsInFi
     * @param playerOne
     * @param playerTwo
     * @return
     */
    public String SaveGame(String uid, int playerOneCounter, int playerTwoCounter, int counter , List<ICard> unused, Map<Integer, ICard> cardsInFi, int playerOne,int playerTwo) {
        IPlayer playerOneForDatabase = modelFactory.createPlayer();
        playerOneForDatabase.setCounter(playerOneCounter);
        playerOneForDatabase.setPid(playerOne);
        IPlayer playerTwoForDatabase = modelFactory.createPlayer();
        playerTwoForDatabase.setCounter(playerTwoCounter);
        playerTwoForDatabase.setPid(playerTwo);
        int counterDatabase = counter;
        Map<Integer, ICard> cardsInField = cardsInFi;
        List<ICard> unusedCards = unused;

        IGame game = modelFactory.createGame();
        game.setId(uid);
        game.setPlayerOne(playerOneForDatabase);
        game.setPlayerTwo(playerTwoForDatabase);
        game.setCounter(counterDatabase);
        game.setCardsInField(cardsInField);
        game.setUnusedCards(unusedCards);
        IGameDao dao = this.gameDao;

        dao.createOrUpdateGame(game);

        dao.closeDb();
        return  game.getId();

    }

    public IGame getGame(String uid) {

        IGameDao dao = gameDao;
        IGame game = dao.findGame(uid);
        dao.closeDb();
        return game;
    }
}
