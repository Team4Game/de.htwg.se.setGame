package de.htwg.se.setgame.util.persistence.couchdb;

import com.google.inject.Inject;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.util.persistence.IGameDao;
import org.ektorp.ViewQuery;

import java.util.List;

public class GameDao implements IGameDao {


    private final Mapper mapper;
    @Inject
    public GameDao(IModelFactory modelFactory) {


        this.mapper = new Mapper(modelFactory);


    }

	@Override
	public void createOrUpdateGame(IGame game) {
        IGame persistentGame = mapper.getPersistentGame(game);
        if (findGame(game.getId()) == null) {
            CouchDBSession.getCouchDbConnector().create(persistentGame);
        } else {
            IGame persGame = findGame(game.getId());
            persGame.setPlayerOne(game.getPlayerOne());
            persGame.setPlayerTwo(game.getPlayerTwo());
            persGame.setCardsInField(game.getCardsInField());
            persGame.setUnusedCards(game.getUnusedCards());
            persGame.setCounter(game.getCounter());
            CouchDBSession.getCouchDbConnector().update(persGame);
        }
    }

	@Override
    public IGame findGame(String gameID) {
        ViewQuery query = new ViewQuery()
                .designDocId("_design/findid")
                .viewName("game")
                .key(gameID);
        List<PersistentGame> result = null;

        result = CouchDBSession.getCouchDbConnector().queryView(query, PersistentGame.class);

        if (result.size() == 0) {
            return null;
        }
        return result.get(0);
    }

	@Override
	public void closeDb() {
		// nothing to do here
		return;
	}

}
