package de.htwg.se.setgame.util.persistance.couchDB.pojo;

import de.htwg.se.setgame.model.IPlayer;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by David on 04.01.16.
 */
public class Player extends de.htwg.se.setgame.model.impl.Player{

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
