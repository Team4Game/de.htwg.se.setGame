package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IModelFactoryPersistent;
import de.htwg.se.setgame.model.ModelFactoryCouchDb;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistence.IGameDao;

public class GameDao implements IGameDao {

	private CouchDbConnector db;
	private Mapper mapper;
    IModelFactoryPersistent modelFactoryPersistent;
    IModelFactory modelFactory;
	public GameDao(IModelFactoryPersistent modelFactoryPersistent , IModelFactory modelFactory) {
		this.modelFactory = modelFactory;
        this.modelFactoryPersistent = (ModelFactoryCouchDb) modelFactoryPersistent;

        this.mapper = new Mapper();
        HttpClient httpClient = new StdHttpClient.Builder()
				.host("lenny2.in.htwg-konstanz.de").port(5984).build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		db = new StdCouchDbConnector("setgame", dbInstance);
		db.createDatabaseIfNotExists();
	    this.modelFactory = modelFactory;
        this.modelFactoryPersistent = modelFactoryPersistent;

    }

	@Override
	public void createOrUpdateGame(IGame game) {
		PersistentGame persistentGame = mapper.getPersistentGame(game,modelFactoryPersistent,modelFactory);
		db.create(persistentGame);
	}

	@Override
	public IGame findGame(String id) {
		try {
			PersistentGame persistentGame = db.get(PersistentGame.class, id);
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
