package de.htwg.se.setgame.util.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

/**
 * Created by raina on 03.06.2015.
 */
public class PersistentPlayer extends CouchDbDocument {
	
	private static final long serialVersionUID = 8022990459802689763L;
	
	private int pid;
	private int counter;
	
	public PersistentPlayer() {};
	
	public PersistentPlayer(int pid, int counter) {
		this.pid = pid;
		this.counter = counter;
	}

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
