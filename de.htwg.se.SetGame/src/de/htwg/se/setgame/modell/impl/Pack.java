package de.htwg.se.setgame.modell.impl;

import java.util.LinkedList;
import java.util.List;

import de.htwg.se.setgame.modell.ICard;

/**
 * @author David Simon & Raina Bertolini class use to create all the
 *         combinations of the pack
 */
public class Pack implements de.htwg.se.setgame.modell.IPack {

	/**
	 * Instance variable
	 */
	private ICard pack[] = creatCards();
	public static final String[] FORME = { "ovally", "wave", "balk" };
	public static final String[] COLORS = { "red", "green", "purple" };
	public static final String[] FILL = { "halffill", "fill", "empty" };
	public static final int[] NUMBEROFCOMPONET = { 1, 2, 3 };
	private static final int SIZEOFARRAY = 81;
	private static final int NUMBEROFEACHCARD = 3;
	private int colorIndex = 0;
	private int formeIndex = 0;
	private int fillgingIndex = 0;
	private int numbersIdex = 0;

	/**
	 * Construct for card
	 */

	public Pack() {

	}

	/**
	 * @return the finish pack of the Game
	 */
	protected Card[] creatCards() {
		Card list[] = new Card[SIZEOFARRAY];
		for (int i = 0; i < SIZEOFARRAY; i++) {

			list[i] = new Card(COLORS[colorIndex], FORME[formeIndex],
					FILL[fillgingIndex], NUMBEROFCOMPONET[numbersIdex]);
			setFormedIndex();

		}

		return list;

	}

	/**
	 * set number of index form
	 */
	private void setFormedIndex() {
		int t = this.formeIndex + 1;
		if (t == NUMBEROFEACHCARD) {
			setFillIndex();
			this.formeIndex = 0;
		} else {
			this.formeIndex = t;
		}

	}

	/**
	 * set index of the fill
	 */
	private void setFillIndex() {
		int t = this.fillgingIndex + 1;
		if (t == NUMBEROFEACHCARD) {
			this.fillgingIndex = 0;
			setComponentsOfIndex();
		} else {
			this.fillgingIndex = t;
		}

	}

	/**
	 * set index of number of Components
	 */
	private void setComponentsOfIndex() {
		int t = this.numbersIdex + 1;
		if (t == NUMBEROFEACHCARD) {
			this.numbersIdex = 0;
			setColorIndex();
		} else {
			this.numbersIdex = t;
		}
	}

	/**
	 * Set Color Index
	 */
	private void setColorIndex() {
		int t = this.colorIndex + 1;
		if (t == NUMBEROFEACHCARD) {
			this.colorIndex = 0;
		}
		this.colorIndex = t;

	}

	/**
	 * @return pack of cards
	 */
	@Override
	public List<ICard> getPack() {
		List<ICard> liste = new LinkedList<ICard>();
		for (ICard card : this.pack) {
			liste.add(card);
		}
		return liste;
	}
}
