package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IPlayer;

/**
 * Created by raina on 03.06.2015.
 */
public class Player implements IPlayer {
	
	private int pid;
	private int counter;
	public Player(){

    }
	
	@Override
	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public int getCounter() {
		return this.counter;
	}

	@Override
	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public int getPid() {
		return this.pid;
	}
	
	
	/*
	this.playerOne = 1;
	this.playerTwo = 2;
	this.playerOneCounter = 0;
	this.playerTwoCounter = 0;
	*/
}
