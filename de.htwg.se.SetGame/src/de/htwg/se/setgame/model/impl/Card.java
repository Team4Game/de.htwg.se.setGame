package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.ACard;
import de.htwg.se.setgame.model.ICard;

/**
 * @author David Simon & Raina Bertolini
 * 
 */

public class Card extends ACard {
	private String color;
	private String form;
	private String panelFilling;
	private int anz;

	/**
	 * This method create a new card object
	 * 
	 * @param color
	 * @param form
	 * @param panelFilling
	 * @param amount
	 */
	public Card(String color, String form, String panelFilling, int amount) {
		this.setColor(color);
		this.setFomr(form);
		this.setPanelFilling(panelFilling);
		this.setNumberOfComponents(amount);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.impl.ICard#getColor()
	 */
	@Override
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            set the color of a card
	 */
	private void setColor(String color) {
		if (color.equals(Pack.COLORS[0]) || color.equals(Pack.COLORS[1])
				|| color.equals(Pack.COLORS[2])) {
			this.color = color;
		} else {
			this.color = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.impl.ICard#getFomr()
	 */
	@Override
	public String getForm() {
		return form;
	}

	/**
	 * @param fomr
	 *            set the form of the Card
	 * @throws IllegalArgumentException
	 *             if the form is not allowed
	 */
	private void setFomr(String fomr) {
		if (fomr.equals(Pack.FORME[2]) || fomr.equals(Pack.FORME[0])
				|| fomr.equals(Pack.FORME[1])) {
			this.form = fomr;
		} else {
			this.form = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.impl.ICard#getPanelFilling()
	 */
	@Override
	public String getPanelFilling() {
		return panelFilling;
	}

	/**
	 * @param panelFilling
	 *            filling of the card
	 * @throws IllegalArgumentException
	 *             if filling is not allowed
	 */
	private void setPanelFilling(String panelFilling) {
		if (panelFilling.equals(Pack.FILL[0])
				|| panelFilling.equals(Pack.FILL[1])
				|| panelFilling.equals(Pack.FILL[2])) {
			this.panelFilling = panelFilling;
		} else {
			this.panelFilling = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.impl.ICard#getNumberOfComponents()
	 */
	public int getNumberOfComponents() {
		return anz;
	}

	/**
	 * @param numberOfComponents
	 * @throws IllegalArgumentException
	 *             if numberOfComponents is not allowed
	 */

	private void setNumberOfComponents(int numberOfComponents) {
		if (numberOfComponents == Pack.NUMBEROFCOMPONET[0]
				|| numberOfComponents == Pack.NUMBEROFCOMPONET[1]
				|| numberOfComponents == Pack.NUMBEROFCOMPONET[2]) {
			this.anz = numberOfComponents;

		} else {
			this.anz = -1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.impl.ICard#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("|" + this.color + "| \n " + "|" + this.form + "| \n " + "|"
				+ this.panelFilling + "| \n " + "|" + this.anz + "|\n");
		return sb.toString();
	}



	@Override
	public boolean comparTo(ICard card) {

		if (this.color.equals(card.getColor()) && this.anz == card.getNumberOfComponents()
				&& this.form.equals(card.getForm())
				&& this.panelFilling.equals(card.getPanelFilling())) {
			return true;
		}
		return false;

	}
}
