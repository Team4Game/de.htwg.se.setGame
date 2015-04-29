package de.htwg.se.setgame.controller.impl;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPack;
import de.htwg.se.setgame.model.impl.Field;
import de.htwg.se.setgame.model.impl.Pack;
import de.htwg.se.setgame.util.observer.Observable;



/**
 * @author raina
 *
 */
public class SetController extends Observable implements IController {

	/**
	 *  field
	 */
	private IField field;

	/**
	 * counter
	 */
	private int counter;

	/**
	 * IPack
	 */
	private IPack pack;

	/**
	 * number for set
	 */
	private static final int NUMBEROFSETCARDS = 3;
	/**
	 * 		numforgotrguth;
	 */
	private static final int THREE = 3;
	/**
	 * a number for
	 */
	private static final int THOUSAND = 1000;
	/**
	 * 
	 */
	private final int playerOne;
	/**
	 * 
	 */
	private final int playerTwo;
	/**
	 * 
	 */
	private int playerOneCounter;
	/**
	 * 
	 */
	private int playerTwoCounter;

	/**
	 * Logic Construct make for the game a new field with a new pack!!!
	 */
	@Inject
	public SetController(IModelFactory modelFactory) {
		this.field = modelFactory.createField();
		this.counter = 0;
		this.field.startUp();
		this.playerOne = 1;
		this.playerTwo = 2;
		this.playerOneCounter = 0;
		this.playerTwoCounter = 0;
		checkIfIsASeTInGame();
		pack = new Pack();
	}
	@Override
	public void newGame(){
		this.field.clear();
		this.counter = 0;
		this.playerOneCounter = 0;
		this.playerTwoCounter = 0;
		notifyObservers();
	}

	/**
	 * 
	 */
	protected void checkIfIsASeTInGame() {
		List<ICard> liste = new LinkedList<ICard>();
		liste.addAll(getSet(this.field.getAllCardsInGame()));
		if(liste.size() < NUMBEROFSETCARDS){
			int i = 0;
			while(!changeCardsInGame() && i < THOUSAND){
				i++;
			}
			
		}
		changeCardsInGame();
		notifyObservers();
	}


	/**
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 * @return true if all the cards are in the field is only a safety Method
	 */
	protected boolean isInField(ICard cardOne, ICard cardTwo, ICard cardThree) {
		this.counter = 0;
		for (ICard card : field.getCardsInField()) {
			if (card.comparTo(cardOne) || card.comparTo(cardTwo)
					|| card.comparTo(cardThree)) {
				counter++;
			}
		}
		if (this.counter == NUMBEROFSETCARDS) {
			return true;
		}
		return false;

	}
 
	/**
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 * @return return true if is a set.
	 */
	protected boolean isASet(ICard cardOne, ICard cardTwo, ICard cardThree) {

		if (!isInField(cardOne, cardTwo, cardThree)) {
			return false;
		} else {
			if (proveIfIsASet(cardOne, cardTwo, cardThree)) {
				field.foundSet(cardOne, cardTwo, cardThree);
				if(getASetInGame().size() >= THREE){
					return true;
				} else if (allTheSetsInField(this.field.getAllCardsInGame())) {
					changeCardsInGame();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param list is the Cards in field the new ones if there is no set anymore.
	 * @return true if still set in fields
	 */
	protected boolean allTheSetsInField(List<ICard> list) {
		if (!getSet(list).isEmpty()) {
			return true;
		}
		if (changeCardsInGame()) {
			return true;
		}
 
		return false;
 
	}
	@Override
	public void setFieldSize(int size){
		if(size > 0){
			this.field.setSizeOfField(size);
		}
		checkIfIsASeTInGame();
	}

	/**
	 * changed the Cards in the field if necessary. to
	 */
	/**
	 * @return
	 */
	private boolean changeCardsInGame() {
		List<ICard> allCards = new LinkedList<ICard>();
		allCards.addAll(field.getUnusedCards());
		if (!allCards.isEmpty() && !getSet(allCards).isEmpty()) {
			field.changeCards(getSet(allCards));
			return true;
		}
		return false;

	}

	/**
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 * @return
	 */
	private boolean proveColor(ICard cardOne, ICard cardTwo, ICard cardThree) {
		return proveString(cardOne.getColor(), cardTwo.getColor(),
				cardThree.getColor());
	}

	/**
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 * @return
	 */
	private boolean proveNumberOfComponents(ICard cardOne, ICard cardTwo,
			ICard cardThree) {
		if (cardOne.getNumberOfComponents() == cardTwo.getNumberOfComponents()
				&& cardOne.getNumberOfComponents() == cardThree
						.getNumberOfComponents()) {
			return true;
		} else if (cardOne.getNumberOfComponents() != cardTwo
				.getNumberOfComponents()
				&& cardOne.getNumberOfComponents() != cardThree
						.getNumberOfComponents()
				&& cardTwo.getNumberOfComponents() != cardThree
						.getNumberOfComponents()) {
			return true;
		}
		return false;
	}

	/**
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 * @return
	 */
	private boolean proveFilling(ICard cardOne, ICard cardTwo, ICard cardThree) {
		return proveString(cardOne.getPanelFilling(),
				cardTwo.getPanelFilling(), cardThree.getPanelFilling());
	}

	/**
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 * @return
	 */
	private boolean proveForm(ICard cardOne, ICard cardTwo, ICard cardThree) {
		return proveString(cardOne.getForm(), cardTwo.getForm(),
				cardThree.getForm());
	}

	/**
	 * @param stringOne
	 * @param stringTwo
	 * @param stringThree
	 * @return
	 */
	private boolean proveString(String stringOne, String stringTwo,
			String stringThree) {
		if (stringOne.compareTo(stringTwo) == 0
				&& stringOne.compareTo(stringThree) == 0 && stringTwo.compareTo(stringThree) == 0) {
			return true;
		} else if (stringOne.compareTo(stringTwo) != 0
				&& stringOne.compareTo(stringThree) != 0
				&& stringTwo.compareTo(stringThree) != 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param cardOne
	 * @param cardTwo
	 * @param cardThree
	 * @return
	 */
	private boolean proveIfIsASet(ICard cardOne, ICard cardTwo, ICard cardThree) {
		if (proveColor(cardOne, cardTwo, cardThree)
				&& proveFilling(cardOne, cardTwo, cardThree)
				&& proveNumberOfComponents(cardOne, cardTwo, cardThree)
				&& proveForm(cardOne, cardTwo, cardThree)) {
			return true;
		}
		return false;

	}

	/**
	 * @param list
	 * @return
	 */
	private List<ICard> getSet(List<ICard> list) {
		LinkedList<ICard> setList = new LinkedList<ICard>();
		if (list.size() >= NUMBEROFSETCARDS) {

			for (ICard cardOne : list) {
				for (ICard cardTwo : list) {
					if (!cardOne.equals(cardTwo)) {
						for (ICard cardThree : list) {

							if (proveIfIsASet(cardOne, cardTwo, cardThree)
									&& !cardThree.equals(cardOne)
									&& !(cardTwo.equals(cardThree))) {

								setList.add(cardOne);
								setList.add(cardTwo);
								setList.add(cardThree);
								return setList;
							}
						}
					}
				}
			}

		}

		return setList;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getCardinGame()
	 */
	@Override
	public List<ICard> getCardinGame() {
		return this.field.getAllCardsInGame();
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getField()
	 */
	@Override
	public Field getField() {
		return (Field) this.field;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#areSetInField()

	private boolean areSetInField() {
		LinkedList<ICard> liste = new LinkedList<ICard>();
		liste.addAll(getSet(this.field.getCardsInField()));
		if (liste.isEmpty()) {
			return changeCardsInGame();

		}
		return true;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#isASetForController(de.htwg.se.setgame.modell.impl.Card, de.htwg.se.setgame.modell.impl.Card, de.htwg.se.setgame.modell.impl.Card, int)
	 */
	@Override
	public void isASetForController(ICard cardOne, ICard cardTwo,
									ICard cardThree, int player) {
		if (isASet(cardOne, cardTwo, cardThree)) {
			if (this.playerOne == player) {
				this.playerOneCounter = this.playerOneCounter + 1;
			} else if (this.playerTwo == player) {
				this.playerTwoCounter = this.playerTwoCounter + 1;
			
			}
			if (playerOne == player || player == this.playerTwo || player >= 0) {
				notifyObservers();
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getASetInGame()
	 */
	@Override
	public List<ICard> getASetInGame(){
		return getSet(this.field.getCardsInField());
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#stillSetInGame()
	 */
	@Override
	public boolean stillSetInGame() {
		LinkedList<ICard> liste = new LinkedList<ICard>();
		liste.addAll(getSet(this.field.getAllCardsInGame()));
		if(liste.isEmpty()){
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getSetInField()
	 */
	@Override
	public List<ICard> getSetInField(){
		return getSet(this.field.getCardsInField());
		
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerOnePoints()
	 */
	@Override
	public int getPlayerOnePoints(){
        return this.playerOneCounter;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerTwoPoints()
	 */
	@Override
	public int getPlayerTwoPoints(){
		return this.playerTwoCounter;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerOne()
	 */
	@Override
	public int getPlayerOne(){
		return this.playerOne;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerTwo()
	 */
	@Override
	public int getPlayerTwo(){
		return this.playerTwo;
	}

	@Override
	public List<ICard> getCardInFieldGame() {
		return this.field.getCardsInField();
	}

	@Override
	public Map<Integer, ICard> getCardsAndTheIndexOfCardInField() {
		return this.field.getCardInFieldGame();
	}

	@Override
	public IPack getPack() {
		return pack;
	}
}
