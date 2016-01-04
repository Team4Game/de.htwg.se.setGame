package de.htwg.se.setgame.model;

import java.util.List;
import java.util.Map;

/**
 * Created by raina on 03.06.2015.
 */
public interface IGame {

	void setPlayerOne(IPlayer player1);
	
	void setPlayerTwo(IPlayer player2);

	void setCardInField(Map<Integer, ICard> cardsInField);

	void setUnusedCards(List<ICard> unusedCards);

	void setGameToken(String token);

	IPlayer getPlayerOne();

	IPlayer getPlayerTwo();

	Map<Integer, ICard> getCardInField();

	List<ICard> getUnusedCards();

	String getToken();

}
