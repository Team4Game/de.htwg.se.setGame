package de.htwg.se.setgame.util.persistance.hibernate;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistance.IGameDao;

/**
 * Created by David on 04.01.16.
 */
public class GameDao implements IGameDao {
    @Override
    public boolean createOrUpdate(IGame game) {
        return false;
    }

    @Override
    public IGame findGame(String id) {
        return null;
    }

    @Override
    public void closeDb() {

    }
}
