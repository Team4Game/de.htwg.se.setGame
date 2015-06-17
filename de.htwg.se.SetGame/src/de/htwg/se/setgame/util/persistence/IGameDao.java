package de.htwg.se.setgame.util.persistence;

import de.htwg.se.setgame.model.IGame;

public interface IGameDao {
    /**
     *
     * @param game for database
     */
	void createOrUpdateGame(IGame game);

    /**
     *
     * @param id id of game
     * @return game
     */
	IGame findGame(String id);

    /**
     * close database
     */
	void closeDb();
	
}
