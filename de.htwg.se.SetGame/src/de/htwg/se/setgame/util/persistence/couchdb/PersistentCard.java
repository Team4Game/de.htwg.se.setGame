package de.htwg.se.setgame.util.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;

/**
 * @author David Simon & Raina Bertolini
 * 
 */

public class PersistentCard extends CouchDbDocument implements ICard {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9000465800577475080L;
	
	private String color;
	private String form;
	private String panelFilling;
	private int anz;

	public PersistentCard() {};
	
	/**
	 * This method create a new card object
	 * 
	 * @param color
	 * @param form
	 * @param panelFilling
	 * @param amount
	 */
	public PersistentCard(String color, String form, String panelFilling, int amount) {
		this.setColor(color);
		this.setForm(form);
		this.setPanelFilling(panelFilling);
		this.setNumberOfComponents(amount);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.impl.ICard#getColor()
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color
	 *            set the color of a card
	 */
	public void setColor(String color) {
		if (color.equals(CardAtributen.COLORS[0]) || color.equals(CardAtributen.COLORS[1])
				|| color.equals(CardAtributen.COLORS[2])) {
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
	public String getForm() {
		return form;
	}

	/**
	 * @param form
	 *            set the form of the Card
	 * @throws IllegalArgumentException
	 *             if the form is not allowed
	 */
	public void setForm(String form) {
		if (form.equals(CardAtributen.FORME[2]) || form.equals(CardAtributen.FORME[0])
				|| form.equals(CardAtributen.FORME[1])) {
			this.form = form;
		} else {
			this.form = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.impl.ICard#getPanelFilling()
	 */
	public String getPanelFilling() {
		return panelFilling;
	}

	/**
	 * @param panelFilling
	 *            filling of the card
	 * @throws IllegalArgumentException
	 *             if filling is not allowed
	 */
	public void setPanelFilling(String panelFilling) {
		if (panelFilling.equals(CardAtributen.FILL[0])
				|| panelFilling.equals(CardAtributen.FILL[1])
				|| panelFilling.equals(CardAtributen.FILL[2])) {
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
	public void setNumberOfComponents(int numberOfComponents) {
		if (numberOfComponents == CardAtributen.NUMBEROFCOMPONET[0]
				|| numberOfComponents == CardAtributen.NUMBEROFCOMPONET[1]
				|| numberOfComponents == CardAtributen.NUMBEROFCOMPONET[2]) {
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("|" + this.color + "| \n " + "|" + this.form + "| \n " + "|"
				+ this.panelFilling + "| \n " + "|" + this.anz + "|\n");
		return sb.toString();
	}



	public boolean compareTo(ICard card) {

		if (this.color.equals(card.getColor()) && this.anz == card.getNumberOfComponents()
				&& this.form.equals(card.getForm())
				&& this.panelFilling.equals(card.getPanelFilling())) {
			return true;
		}
		return false;

	}
}
