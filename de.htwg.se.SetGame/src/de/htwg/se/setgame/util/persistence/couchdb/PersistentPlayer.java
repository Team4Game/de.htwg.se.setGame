package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.impl.Player;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by raina on 03.06.2015.
 */

public class PersistentPlayer extends Player {

    private String dbIdPlayer;
    private String dbRevPlayer;

    @JsonProperty("_id")
    public String getDbIdPlayer() {
        return dbIdPlayer;
    }

    @JsonProperty("_id")
    public void setDbIdPlayer(String dbIdPlayer) {
        this.dbIdPlayer = dbIdPlayer;
    }

    @JsonProperty("_rev")
    public void setDbRevPlayer(String dbRevPlayer) {
        this.dbRevPlayer = dbRevPlayer;
    }

    @JsonProperty("_rev")
    public String getDbRevPlayer() {
        return dbRevPlayer;
    }



}
