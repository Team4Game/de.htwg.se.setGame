package de.htwg.se.setgame.model;

/**
 * Created by raina on 03.06.2015.
 */

import de.htwg.se.setgame.util.persistence.couchdb.PersistentPlayer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonDeserialize(as= PersistentPlayer.class)
public interface IPlayer {
	
	void setPid(int pid);
	
	void setCounter(int counter);
	
	int getPid();
	
	int getCounter();
	
}
