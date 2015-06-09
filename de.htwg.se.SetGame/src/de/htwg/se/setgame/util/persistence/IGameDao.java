package de.htwg.se.setgame.util.persistence;

import de.htwg.se.setgame.util.persistence.couchdb.PersistentGame;

public interface IGameDao {

	// creates or updates game
	void createOrUpdateGame(PersistentGame game);
	
	// get game
	PersistentGame findGame(String id);
	
	// delete game
	void deleteGame(String id);
	
}
