package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.impl.Player;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.ektorp.support.CouchDbDocument;

/**
 * Created by raina on 03.06.2015.
 */

public class PersistentPlayer extends Player {

    private String dbId;
    private String dbRev;

    @JsonProperty("_id")
    public String getDbId() {
        return dbId;
    }

    @JsonProperty("_id")
    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    @JsonProperty("_rev")
    public String getDbRev() {
        return dbRev;
    }

    @JsonProperty("_rev")
    public void setDbRev(String dbRev) {
        this.dbRev = dbRev;
    }

}
