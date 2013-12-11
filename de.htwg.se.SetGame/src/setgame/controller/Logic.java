package setgame.controller;

import java.util.LinkedList;

import de.htwg.se.observer.Observable;
import setgame.modell.Card;

public class Logic extends Observable {
	setgame.modell.Field field;
	LinkedList<Card> listOfSetsInField;

	public Logic() {
		this.field = new setgame.modell.Field();
	}

	public void stratGame() {
	}

	public boolean isInFiel(Card cardOne, Card cardTwo, Card cardThree) {
		int i = 3;
		for (Card card : field.cardsInField())
			if (card.getColor().equals(cardOne.getColor())
					&& card.getFomr().equals(cardOne.getFomr())
					&& card.getNumberOfComponents() == cardOne
							.getNumberOfComponents()
					&& card.getPanelFilling().equals(cardOne.getPanelFilling())
					|| card.getColor().equals(cardTwo.getColor())
					&& card.getFomr().equals(cardTwo.getFomr())
					&& card.getNumberOfComponents() == cardTwo
							.getNumberOfComponents()
					&& card.getPanelFilling().equals(cardTwo.getPanelFilling())
					|| card.getColor().equals(cardThree.getColor())
					&& card.getFomr().equals(cardThree.getFomr())
					&& card.getNumberOfComponents() == cardThree
							.getNumberOfComponents()
					&& card.getPanelFilling().equals(
							cardThree.getPanelFilling())) {
				i++;
			}

		if (i == 3)
			return true;
		else
			return false;
	}

	public boolean isAset(Card cardOne, Card cardTwo, Card cardThree) {
		if (!isInFiel(cardOne, cardTwo, cardThree))
			return false;
		else {
			if (proveColor(cardOne, cardTwo, cardThree)
					&& proveFilling(cardOne, cardTwo, cardThree)
					&& proveNumberOfComponents(cardOne, cardTwo, cardThree)
					&& proveFilling(cardOne, cardTwo, cardThree)) {
				field.foundSet(cardOne, cardTwo, cardThree);
				alltheSetsInField(field.cardsInField());
				return true;
			} else {
				return false;
			}
		}

	}

	private void alltheSetsInField(LinkedList<Card> fielCards) {
		boolean foudSet = false;
		for(Card cardOne : fielCards){
			for(Card cardTwo : fielCards)
				for(Card cardThree : fielCards){
					if(isAset(cardOne, cardTwo, cardThree)){
						foudSet = true;
						break;
					}
				}
		}
		if(foudSet == false){
			changeCardsinGame();
		}
		
	}

	private void changeCardsinGame() {
		
	}

	private boolean proveColor(Card cardOne, Card cardTwo, Card cardThree) {
		if (cardOne.getColor().equals(cardTwo.getColor())
				&& cardOne.getColor().equals(cardTwo.getColor())) {
			return true;
		} else if ((false == (cardOne.getColor().equals(cardTwo.getColor())))
				&& (false == (cardOne.getColor().equals(cardThree.getColor())))
				&& (false == (cardTwo.getColor().equals(cardThree.getColor())))) {
			return true;
		}
		return false;
	}

	private boolean proveNumberOfComponents(Card cardOne, Card cardTwo,
			Card cardThree) {
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

	private boolean proveFilling(Card cardOne, Card cardTwo, Card cardThree) {
		if (cardOne.getPanelFilling().equals(cardTwo.getPanelFilling())
				&& cardOne.getPanelFilling().equals(cardTwo.getPanelFilling())) {
			return true;
		} else if ((false == (cardOne.getPanelFilling().equals(cardTwo
				.getPanelFilling())))
				&& (false == (cardOne.getColor().equals(cardThree.getColor())))
				&& (false == (cardTwo.getColor().equals(cardThree.getColor())))) {
			return true;
		}
		return false;
	}
}
