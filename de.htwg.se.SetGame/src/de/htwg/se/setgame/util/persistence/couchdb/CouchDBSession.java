package de.htwg.se.setgame.util.persistence.couchdb;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

/**
 * Created by raina on 16.06.2015.
 */
public class CouchDBSession {
    private static CouchDbConnector couchDbConnector;
    private static CouchDbConnector couchDbConnectorView;

    private CouchDBSession(){

    }

    public static CouchDbConnector getCouchDbConnector(){
        if(couchDbConnector == null){
            HttpClient httpClient = new StdHttpClient.Builder()
                    .host("lenny2.in.htwg-konstanz.de").port(5984).build();

            CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
            couchDbConnector = new StdCouchDbConnector("a_setgame", dbInstance);
            couchDbConnector.createDatabaseIfNotExists();
        }
        return couchDbConnector;
    }

}
