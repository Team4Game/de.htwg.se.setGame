package de.htwg.se.setgame.util.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by raina on 03.06.2015.
 */

@Entity
@Table(name = "player")
public class PersistentPlayer implements Serializable {
	
	private static final long serialVersionUID = 4826196614879025625L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private int pid;
	private int counter;
	
	public PersistentPlayer() {};
	
/*	public PersistentPlayer(int pid, int counter) {
		this.pid = pid;
		this.counter = counter;
	}*/

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public int getCounter() {
		return this.counter;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPid() {
		return this.pid;
	}

}
