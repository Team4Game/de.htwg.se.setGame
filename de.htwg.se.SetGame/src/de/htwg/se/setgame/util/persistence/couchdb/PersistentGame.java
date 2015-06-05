package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;

import java.util.List;

import org.ektorp.support.CouchDbDocument;

/**
 * Created by miwalz on 03.06.2015.
 */
public class PersistentGame extends CouchDbDocument implements IGame {

	private static final long serialVersionUID = 9152157365405198118L;

	private IPlayer player1;
	private IPlayer player2;

	private List<ICard> cardsInField; // get via Field.getCardsInField
	private List<ICard> unusedCards; // get via Pack.getPack

	private String token;

	public PersistentGame(IPlayer player1, IPlayer player2, List<ICard> cardsInField,
			List<ICard> unusedCards, String token) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.cardsInField = cardsInField;
		this.unusedCards = unusedCards;
		this.token = token;
	}

	@Override
	public void setPlayer(IPlayer playerOne, IPlayer playerTwo) {
		this.player1 = playerOne;
		this.player2 = playerTwo;
	}

	@Override
	public void setCardInField(List<ICard> cardsInField) {
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
	public List<ICard> getCardInField() {
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
}
