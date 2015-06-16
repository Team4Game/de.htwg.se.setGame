package de.htwg.se.setgame.util.persistence;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.model.impl.Game;
import de.htwg.se.setgame.model.impl.Player;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by raina on 17.06.2015.
 */
public class GameDaoDummy implements IGameDao {

    public GameDaoDummy(IModelFactory modelFactory) {

    }

    @Override
    public void createOrUpdateGame(IGame game) {

    }

    @Override
    public IGame findGame(String id) {
        if (id.equals("123")) {
            IGame result = new Game();
            IPlayer player1 = new Player();
            player1.setPid(1);
            player1.setCounter(3);
            IPlayer player2 = new Player();
            player2.setPid(2);
            player2.setCounter(5);
            result.setPlayerOne(player1);
            result.setPlayerTwo(player2);
            result.setCardsInField(new HashMap<Integer, ICard>());
            result.setUnusedCards(new LinkedList<ICard>());
            result.setCounter(3);
            result.setId(id);
            return result;
        } else {
            return null;
        }
    }

    @Override
    public void closeDb() {

    }
}
