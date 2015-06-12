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

	private String id;
	private IPlayer playerOne;
	private IPlayer playerTwo;
	private int counter;
	private Map<Integer, ICard> cardsInField; // get via Field.getCardsInField
	private List<ICard> unusedCards; // get via Pack.getPack

	
	
	public Game(String id, IPlayer playerOne, IPlayer playerTwo, int counter, Map<Integer, ICard> cardsInField2,
			List<ICard> unusedCards) {
		super();
		this.id = id;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.counter = counter;
		this.cardsInField = cardsInField2;
		this.unusedCards = unusedCards;
	}

	
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
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
