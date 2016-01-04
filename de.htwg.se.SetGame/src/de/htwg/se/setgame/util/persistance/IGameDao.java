package de.htwg.se.setgame.util.persistance;

import de.htwg.se.setgame.model.IGame;

/**
 * Created by David on 04.01.16.
 */
public interface IGameDao {

    boolean createOrUpdate(IGame game);

    IGame findGame(String id);

    void closeDb();

}
