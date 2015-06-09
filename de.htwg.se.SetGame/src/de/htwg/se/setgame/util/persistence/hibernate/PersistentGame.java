package de.htwg.se.setgame.util.persistence.hibernate;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by miwalz on 03.06.2015.
 */

@Entity
@Table(name = "setgame")
public class PersistentGame implements IGame {

	@Id  
	private int id;
	
	private IPlayer player1;
	private IPlayer player2;

	@OneToMany(mappedBy="persistentGame")
	private List<ICard> cardsInField; // get via Field.getCardsInField
	
	@OneToMany(mappedBy="persistentGame")
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
