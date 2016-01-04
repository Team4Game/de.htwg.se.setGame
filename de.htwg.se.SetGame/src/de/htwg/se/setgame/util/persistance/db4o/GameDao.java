package de.htwg.se.setgame.util.persistance.db4o;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Predicate;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistance.IGameDao;

/**
 * Created by David on 04.01.16.
 */
public class GameDao implements IGameDao{

    private static ObjectContainer db;
    public GameDao(){

    }

    public static ObjectContainer getDb(){
        if (db == null){
            EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
            db = Db4oEmbedded.openFile(configuration, "setGame.db");
        }
        return db;
    }

    @Override
    public boolean createOrUpdate(IGame game) {
        saveGame(game);
        if(findGame(game.getToken()) == null){
            return false;
        }
        return true;
    }

    @Override
    public IGame findGame(final String id) {
        IGame result = null;
        ObjectSet<IGame> dbResult = getDb().query(new Predicate<IGame>() {
            @Override
            public boolean match(IGame game) {
                return game.getToken().equals(id);
            }
        });
        if(dbResult.isEmpty()) {
            return result;
        } else {
            result = dbResult.get(0);
        }
        return result;
    }

    @Override
    public void closeDb() {
        getDb().close();
    }

    private void saveGame(IGame game){
        getDb().store(game);
    }
}
