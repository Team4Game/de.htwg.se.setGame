package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;

import java.util.List;
import java.util.Map;

/**
 * Created by raina on 03.06.2015.
 */
public class Game implements IGame {

	private String gameID;
	private IPlayer playerOne;
	private IPlayer playerTwo;
	private int counter;
    // get via Field.getCardsInField
	private Map<Integer, ICard> cardsInField;
    // get via Pack.getPack
    private List<ICard> unusedCards;

	public Game(){

    }

	
	
	@Override
	public String getId() {
		return gameID;
	}

	@Override
	public void setId(String id) {
		this.gameID = id;
	}

	@Override
	public IPlayer getPlayerOne() {
		return playerOne;
	}

	@Override
	public void setPlayerOne(IPlayer playerOne) {
		this.playerOne = playerOne;
	}

	@Override
	public IPlayer getPlayerTwo() {
		return playerTwo;
	}

	@Override
	public void setPlayerTwo(IPlayer playerTwo) {
		this.playerTwo = playerTwo;
	}

	@Override
	public int getCounter() {
		return counter;
	}

	@Override
	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public Map<Integer, ICard> getCardsInField() {
		return cardsInField;
	}

	@Override
	public void setCardsInField(Map<Integer, ICard> cardsInField) {
		this.cardsInField = cardsInField;
	}

	@Override
	public List<ICard> getUnusedCards() {
		return unusedCards;
	}

	@Override
	public void setUnusedCards(List<ICard> unusedCards) {
		this.unusedCards = unusedCards;
	}

}
