package de.htwg.se.setgame.util.persistance.couchDB.pojo;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by David on 04.01.16.
 */
public class Card extends de.htwg.se.setgame.model.impl.Card{
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
