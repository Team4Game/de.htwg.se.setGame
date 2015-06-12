package de.htwg.se.setgame.util.persistence.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistence.IGameDao;

public class GameDao implements IGameDao {
	
	private ObjectContainer db;

	public GameDao() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "setgame.data");
	}

	@Override
	public void createOrUpdateGame(IGame game) {
		this.db.store(game);
	}

	@Override
	public IGame findGame(final String id) {
		ObjectSet<IGame> objectSet = this.db.query(new Predicate<IGame>() {
			private static final long serialVersionUID = 7407252057775053179L;

			@Override
			public boolean match(IGame game) {
				return game.getId().equals(id);
			}
		});
		if (objectSet.isEmpty()) {
			return null;
		}
		return objectSet.get(0);
	}
	
	public void closeDb() {
		this.db.close();
	}

}