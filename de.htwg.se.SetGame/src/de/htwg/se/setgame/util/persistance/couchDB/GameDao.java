package de.htwg.se.setgame.util.persistance.couchDB;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistance.IGameDao;
import de.htwg.se.setgame.util.persistance.couchDB.pojo.Game;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

/**
 * Created by David on 04.01.16.
 */
public class GameDao  implements IGameDao{


    private static final String QUERY = "findBytoken";
    private static CouchDbConnector connector = null;
    private static final int PORT = 5984;
    public GameDao(){

    }


    private static CouchDbConnector getConnector(){
        if(connector == null){
            HttpClient httpClient = new StdHttpClient.Builder().host("lenny2.in.htwg-konstanz.de").port(PORT).build();

            CouchDbInstance instance = new StdCouchDbInstance(httpClient);
            connector = new StdCouchDbConnector("a_david_setGame", instance);
            connector.createDatabaseIfNotExists();
        }
        return connector;
    }

    @Override
    public boolean createOrUpdate(IGame game) {

        if(findGame(game.getToken())==null){
            getConnector().create(GameDaoMapper.getGame(game));
        } else {
            Game toUpadate = (Game) findGame(game.getToken());

            getConnector().update(GameDaoMapper.update(toUpadate, game));
        }

        return false;
    }

    @Override
    public IGame findGame(String id) {
        GameDaoGame game = new GameDaoGame(getConnector());
        return game.findGame(id);
    }

    @Override
    public void closeDb() {
        return;
    }
}
