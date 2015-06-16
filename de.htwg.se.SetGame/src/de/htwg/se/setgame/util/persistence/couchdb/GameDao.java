package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistence.IGameDao;
import org.ektorp.DocumentNotFoundException;

public class GameDao implements IGameDao {

	private Mapper mapper;

    public GameDao() {


        this.mapper = new Mapper();



    }

	@Override
	public void createOrUpdateGame(IGame game) {
		PersistentGame persistentGame = mapper.getPersistentGame(game);
		CouchDBSession.getCouchDbConnector().create(persistentGame);
	}

	@Override
	public IGame findGame(String id) {
		try {
			PersistentGame persistentGame = CouchDBSession.getCouchDbConnector().get(PersistentGame.class, id);
			IGame game = mapper.getGame(persistentGame);
			return game;
        } catch (DocumentNotFoundException e) {
        	return null;
        }
	}

	@Override
	public void closeDb() {
		// nothing to do here
		return;
	}

}
