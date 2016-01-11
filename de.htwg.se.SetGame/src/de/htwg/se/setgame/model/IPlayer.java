package de.htwg.se.setgame.model;


import de.htwg.se.setgame.util.persistance.couchDB.pojo.Player;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Created by raina on 03.06.2015.
 */
@JsonDeserialize(as = Player.class)
public interface IPlayer {
	
	void setId(int id);
	
	void setCounter(int counter);
	
	int getId();
	
	int getCounter();
	
}
