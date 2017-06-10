package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.impl.Game;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by miwalz on 03.06.2015.
 */
public class PersistentGame extends Game {

    private String dbIdGame;
    private String dbRevGame;

    @JsonProperty("_id")
    public void setDbIdGame(String dbIdGame) {
        this.dbIdGame = dbIdGame;
    }
    
    @JsonProperty("_id")
    public String getDbIdGame() {
        return dbIdGame;
    }


    @JsonProperty("_rev")
    public String getDbRevGame() {
        return dbRevGame;
    }

    @JsonProperty("_rev")
    public void setDbRevGame(String dbRevGame) {
        this.dbRevGame = dbRevGame;
    }

}
