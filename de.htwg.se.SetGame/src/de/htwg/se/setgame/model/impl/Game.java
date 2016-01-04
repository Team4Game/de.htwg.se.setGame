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

	private IPlayer player1;
	private IPlayer player2;

	private Map<Integer,ICard> cardsInField; // get via Field.getCardsInField
	private List<ICard> unusedCards; // get via Pack.getPack

	private String token;

	public Game(IPlayer player1, IPlayer player2, Map<Integer,ICard> cardsInField,
			List<ICard> unusedCards, String token) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.cardsInField = cardsInField;
		this.unusedCards = unusedCards;
		this.token = token;
	}

	@Override
	public void setCardInField(Map<Integer,ICard> cardsInField) {
		this.cardsInField = cardsInField;
	}

	@Override
	public void setUnusedCards(List<ICard> unusedCards) {
		this.unusedCards = unusedCards;
	}

	@Override
	public void setGameToken(String token) {
		this.token = token;
	}

	@Override
	public IPlayer getPlayerOne() {
		return this.player1;
	}

	@Override
	public IPlayer getPlayerTwo() {
		return this.player2;
	}

	@Override
	public Map<Integer, ICard> getCardInField() {
		return this.cardsInField;
	}

	@Override
	public List<ICard> getUnusedCards() {
		return this.unusedCards;
	}

	@Override
	public String getToken() {
		return this.token;
	}

	@Override
	public void setPlayerOne(IPlayer player1) {
		this.player1 = player1;
	}

	@Override
	public void setPlayerTwo(IPlayer player2) {
		this.player2 = player2;
	}
}
