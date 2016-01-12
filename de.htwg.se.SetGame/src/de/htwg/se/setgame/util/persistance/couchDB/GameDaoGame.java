package de.htwg.se.setgame.util.persistance.couchDB;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistance.IGameDao;
import de.htwg.se.setgame.util.persistance.couchDB.GameDaoMapper;
import de.htwg.se.setgame.util.persistance.couchDB.pojo.Game;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;

import java.util.List;

/**
 * Created by David on 04.01.16.
 */
public class GameDaoGame extends CouchDbRepositorySupport<Game> {


    private static final String QUERY = "findByToken";
    private static CouchDbConnector connector = null;
    CouchDbConnector db;

    protected GameDaoGame(CouchDbConnector db) {
        super(Game.class, db);
        this.db = db;
    }


    @View(name = QUERY, map = "function(doc) {  if(doc.token){emit(doc.token, doc); } }")
    public IGame findGame(String token) {
        ViewQuery query = createQuery(QUERY).key(token);
        List<IGame> resultDB = null;
        resultDB = ((List) db.queryView(query, Game.class));

        if (resultDB == null || resultDB.isEmpty()) {
            return null;
        } else {

            return resultDB.get(0);
        }
    }


}


