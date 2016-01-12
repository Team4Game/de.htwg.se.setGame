package de.htwg.se.setgame.util.persistance.couchDB.pojo;

import de.htwg.se.setgame.model.IGame;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by David on 04.01.16.
 */

public class Game extends de.htwg.se.setgame.model.impl.Game{

    private String dbId;
    private String dbRev;

    @JsonProperty("_id")
    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    @JsonProperty("_id")
    public String getDbId() {
        return dbId;
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
