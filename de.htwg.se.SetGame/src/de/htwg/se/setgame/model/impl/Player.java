package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IPlayer;

/**
 * Created by raina on 03.06.2015.
 */
public class Player implements IPlayer {
	
	private int id;
	private int counter;
	
	public Player() {

	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public int getCounter() {
		return this.counter;
	}
	
	
	/*
	this.playerOne = 1;
	this.playerTwo = 2;
	this.playerOneCounter = 0;
	this.playerTwoCounter = 0;
	*/
}
