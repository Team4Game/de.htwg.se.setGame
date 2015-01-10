package de.htwg.se.setgame.controller;

import java.util.List;
import java.util.Map;

import de.htwg.se.setgame.modell.ICard;
import de.htwg.se.setgame.modell.impl.Field;
import de.htwg.se.setgame.util.observer.IObservable;

/**
 * @author raina
 *
 */
/**
 * @author raina
 *
 */
public interface IController extends IObservable {

	/**
	 * @param cardOne card for set
	 * @param cardTwo card for set
	 * @param cardThree card for set
	 * @param player which player is
	 */
	void isASetForController(ICard cardOne, ICard cardTwo, ICard cardThree, int player);

	/**
	 * @return card in game
	 */
	List<ICard> getCardinGame();

	/**
	 * @return field
	 */
	Field getField();

	/**
	 * @return true is is a set
	 */
	boolean areSetInField();

	/**
	 * @return return a set
	 */
	List<ICard> getASetInGame();

	/**
	 * @return look trough the pack and see if still a set
	 */
	boolean stillSetInGame();

	/**
	 * @return return the set in field
	 */
	List<ICard> getSetInField();

	/**
	 * @return points player one
	 */
	int getPlayerOnePoints();

	/**
	 * @return points player two
	 */
	int getPlayerTwoPoints();

	/**
	 * @return player one
	 */
	int getPlayerOne();

	/**
	 * @return player two
	 */
	int getPlayerTwo();

	/**
	 * 	reset game
	 */
	void newGame();

	/**
	 * @param size set size field
	 */
	void setFieldSize(int size);

	/**
	 * @return cards in field
	 */
	List<ICard> getCardInFieldGame();

	/**
	 * @return index and card in the index
	 */
	Map<Integer, ICard> getCardsAndTheIndexOfCardInField();

}