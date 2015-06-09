package de.htwg.se.setgame.model;

import java.util.List;

/**
 * Created by raina on 03.06.2015.
 */
public interface IGame {

	void setPlayerOne(IPlayer player1);
	
	void setPlayerTwo(IPlayer player2);

	void setCardInField(List<ICard> cardsInField);

	void setUnusedCards(List<ICard> unusedCards);
	
	void setId(String id);

	IPlayer getPlayerOne();

	IPlayer getPlayerTwo();

	List<ICard> getCardInField();

	List<ICard> getUnusedCards();

	String getId();
}
