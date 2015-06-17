package de.htwg.se.setgame.util.persistence.couchdb;

import com.db4o.User;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.Card;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

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

/*    @Override
    public Card deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        ICard result = new Card();
        result.setColor(node.get("color").getTextValue());
        result.setColor(node.get("form").getTextValue());
        result.setColor(node.get("panelFilling").getTextValue());
        result.setNumberOfComponents();

        return result;
    }*/
}
