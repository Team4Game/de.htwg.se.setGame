package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.impl.Card;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author David Simon & Raina Bertolini
 * 
 */

public class PersistentCard extends Card {
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
