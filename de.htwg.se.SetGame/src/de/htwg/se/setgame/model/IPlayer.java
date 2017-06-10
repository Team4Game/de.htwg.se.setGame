package de.htwg.se.setgame.model;

/**
 * Created by raina on 03.06.2015.
 */

import de.htwg.se.setgame.util.persistence.couchdb.PersistentPlayer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonDeserialize(as= PersistentPlayer.class)
public interface IPlayer {
    /**
     *
     * @param pid
     */
	void setPid(int pid);

    /**
     *
     * @param counter
     */
	void setCounter(int counter);

    /**
     *
     * @return which id has player in game
     */
	int getPid();

    /**
     *
     * @return counter of set
     */
	int getCounter();
	
}
