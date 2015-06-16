package de.htwg.se.setgame.util.persistence.hibernate;


import de.htwg.se.setgame.model.IPlayer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by raina on 03.06.2015.
 */

@Entity
@Table(name = "setgame_player")
public class PersistentPlayer implements Serializable, IPlayer {
	
	private static final long serialVersionUID = 4826196614879025625L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long playerID;
    private int pid;
    private int counter;

    public PersistentPlayer() {};
	
/*	public PersistentPlayer(int pid, int counter) {
		this.pid = pid;
		this.counter = counter;
	}*/

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getPid() {
        return this.pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

}
