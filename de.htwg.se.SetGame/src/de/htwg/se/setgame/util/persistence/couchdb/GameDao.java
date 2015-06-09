package de.htwg.se.setgame.util.persistence.couchdb;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import de.htwg.se.setgame.util.persistence.IGameDao;

public class GameDao implements IGameDao {

	private CouchDbConnector db;
	
	public GameDao() {
		HttpClient httpClient = new StdHttpClient.Builder()
				.host("lenny2.in.htwg-konstanz.de").port(5984).build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		db = new StdCouchDbConnector("setgame", dbInstance);
		db.createDatabaseIfNotExists();
	}

	@Override
	public void createOrUpdateGame(PersistentGame game) {
		db.create(game);
	}

	@Override
	public PersistentGame findGame(String id) {
		try {
			PersistentGame persistentGame = db.get(PersistentGame.class, id);
            return persistentGame;
        } catch (DocumentNotFoundException e) {
        	// TODO: err handling
        	return null;
        }
	}

	@Override
	public void deleteGame(String id) {
		PersistentGame persistentGame = this.findGame(id);
		db.delete(persistentGame);
	}

}
