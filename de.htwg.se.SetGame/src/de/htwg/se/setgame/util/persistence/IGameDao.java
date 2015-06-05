package de.htwg.se.setgame.util.persistence;

import de.htwg.se.setgame.model.IGame;

public interface IGameDao {

	// creates or updates game
	void createOrUpdateGame(IGame game);
	
	// get game
	IGame findGame(String id);
	
	// delete game
	void deleteGame(String id);
	
}
