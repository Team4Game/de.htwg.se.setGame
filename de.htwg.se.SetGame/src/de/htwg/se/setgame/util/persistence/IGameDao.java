package de.htwg.se.setgame.util.persistence;

import de.htwg.se.setgame.model.IGame;

public interface IGameDao {

	void createOrUpdateGame(IGame game);
	
	IGame findGame(String id);

	void closeDb();
	
}
