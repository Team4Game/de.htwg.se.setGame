package de.htwg.se.setgame.model;

import de.htwg.se.setgame.util.persistence.couchdb.PersistentGame;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.util.List;
import java.util.Map;

/**
 * Created by raina on 03.06.2015.
 */
@JsonDeserialize(as= PersistentGame.class)
public interface IGame {
    /**
     *
     * @param player1 for game
     */
	void setPlayerOne(IPlayer player1);

    /**
     *
     * @param player2 for game
     */
	void setPlayerTwo(IPlayer player2);

    /**
     *
     * @param cardInField all cards in field
     */
	void setCardsInField(Map<Integer, ICard> cardInField);

    /**
     *
     * @param unusedCards game unused cards
     */
	void setUnusedCards(List<ICard> unusedCards);

    /**
     *
     * @param id set token of game
     */
	void setId(String id);

    /**
     *
     * @param counter set counter of game
     */

	void setCounter(int counter);

    /**
     *
     * @return player one
     */
	IPlayer getPlayerOne();

    /**
     *
     * @return player two
     */
	IPlayer getPlayerTwo();

    /**
     *
     * @return cards that are in field
     */
	Map<Integer, ICard> getCardsInField();

    /**
     *
     * @return unused cards from a game
     */
	List<ICard> getUnusedCards();

    /**
     *
     * @return id from a specific game
     */
	String getId();

    /**
     *
     * @return number of set in game
     */
	int getCounter();
}
