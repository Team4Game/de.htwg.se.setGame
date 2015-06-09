package de.htwg.se.setgame.util.persistence.couchdb;

import java.util.Collection;
import java.util.List;

import org.ektorp.support.CouchDbDocument;

import de.htwg.se.setgame.model.ICard;

/**
 * Created by miwalz on 03.06.2015.
 */
public class PersistentGame extends CouchDbDocument {

	private static final long serialVersionUID = 9152157365405198118L;
	
	private String id;
	
	private PersistentPlayer player1;
	private PersistentPlayer player2;
	
	private int counter;

	private List<PersistentCard> cardsInField; // get via Field.getCardsInField
	private List<ICard> unusedCards; // get via Pack.getPack

	public PersistentGame() {}
	
	@SuppressWarnings("unchecked")
	public PersistentGame(String id, PersistentPlayer player1, PersistentPlayer player2, Collection<? extends PersistentCard> cardsInField2,
			Collection<? extends PersistentCard> unusedCards2, int counter) {
		super();
		this.id = id;
		this.player1 = player1;
		this.player2 = player2;
		this.cardsInField = (List<PersistentCard>) cardsInField2;
		this.unusedCards = (List<ICard>) unusedCards2;
		this.counter = counter;
	}

	public void setCardInField(List<PersistentCard> cardsInField) {
		this.cardsInField = cardsInField;
	}

	public void setUnusedCards(List<ICard> unusedCards) {
		this.unusedCards = unusedCards;
	}
	
	public PersistentPlayer getPlayerOne() {
		return this.player1;
	}

	public PersistentPlayer getPlayerTwo() {
		return this.player2;
	}

	public List<PersistentCard> getCardInField() {
		return this.cardsInField;
	}

	public List<ICard> getUnusedCards() {
		return this.unusedCards;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPlayerOne(PersistentPlayer player1) {
		this.player1 = player1;
		
	}

	public void setPlayerTwo(PersistentPlayer player2) {
		this.player2 = player2;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
