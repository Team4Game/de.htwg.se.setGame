package de.htwg.se.setgame.util.persistence.couchdb;

import java.util.Collection;
import org.ektorp.support.CouchDbDocument;

/**
 * Created by miwalz on 03.06.2015.
 */
public class PersistentGame extends CouchDbDocument {

	private static final long serialVersionUID = 9152157365405198118L;
	
	private String id;
	private PersistentPlayer playerOne;
	private PersistentPlayer playerTwo;
	private int counter;
	private Collection<PersistentCard> cardsInField; // get via Field.getCardsInField
	private Collection<PersistentCard> unusedCards; // get via Pack.getPack

	
	
	public PersistentGame() {}
	
	public PersistentGame(String id, PersistentPlayer playerOne, PersistentPlayer playerTwo, Collection<PersistentCard> cardsInField2,
			Collection<PersistentCard> unusedCards2, int counter) {
		super();
		this.id = id;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.cardsInField = cardsInField2;
		this.unusedCards = unusedCards2;
		this.counter = counter;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PersistentPlayer getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(PersistentPlayer playerOne) {
		this.playerOne = playerOne;
	}

	public PersistentPlayer getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(PersistentPlayer playerTwo) {
		this.playerTwo = playerTwo;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Collection<PersistentCard> getCardsInField() {
		return cardsInField;
	}

	public void setCardsInField(Collection<PersistentCard> cardsInField) {
		this.cardsInField = cardsInField;
	}

	public Collection<PersistentCard> getUnusedCards() {
		return unusedCards;
	}

	public void setUnusedCards(Collection<PersistentCard> unusedCards) {
		this.unusedCards = unusedCards;
	}

}
