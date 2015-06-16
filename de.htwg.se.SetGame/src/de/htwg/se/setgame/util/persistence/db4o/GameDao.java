package de.htwg.se.setgame.util.persistence.db4o;

import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistence.IGameDao;

public class GameDao implements IGameDao {


	public GameDao() {

	}

	@Override
	public void createOrUpdateGame(IGame game) {
        if(findGame(game.getId())== null) {
            Db4oSessionManager.getDbObjectContainer().store(game);
        }else{
            IGame old = findGame(game.getId());
            old.setCounter(game.getCounter());
            old.setPlayerOne(game.getPlayerOne());
            old.setPlayerTwo(game.getPlayerTwo());
            old.setUnusedCards(game.getUnusedCards());
            old.setCardsInField(game.getCardsInField());
            Db4oSessionManager.getDbObjectContainer().store(old);
        }
	}

	@Override
	public IGame findGame(final String id) {
		ObjectSet<IGame> objectSet = Db4oSessionManager.getDbObjectContainer().query(new Predicate<IGame>() {
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
	
	@Override
	public void closeDb() {
        Db4oSessionManager.getDbObjectContainer().close();
	}

}