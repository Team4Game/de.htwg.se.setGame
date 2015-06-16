package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.impl.Card;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author David Simon & Raina Bertolini
 * 
 */

public class PersistentCard extends Card {
    private String dbRevCard;
    private String dbIdCard;
    @JsonProperty("_id")
    public String getDbIdCard() {
        return dbIdCard;
    }

    @JsonProperty("_id")
    public void setDbIdCard(String dbIdCard) {
        this.dbIdCard = dbIdCard;
    }

    @JsonProperty("_rev")
    public String getDbRevCard() {
        return dbRevCard;
    }

    @JsonProperty("_rev")
    public void setDbRevCard(String dbRevCard) {
        this.dbRevCard = dbRevCard;
    }
}
