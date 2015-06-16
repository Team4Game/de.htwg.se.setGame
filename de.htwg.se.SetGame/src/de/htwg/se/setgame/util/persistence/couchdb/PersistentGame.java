package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.model.impl.Game;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by miwalz on 03.06.2015.
 */
public class PersistentGame extends Game {


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

    private String dbId;
    private String dbRev;
}
