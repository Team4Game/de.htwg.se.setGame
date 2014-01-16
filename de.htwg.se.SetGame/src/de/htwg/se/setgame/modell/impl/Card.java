package de.htwg.se.setgame.modell.impl;


/**
 * @author David Simon & Raina Bertolini
 *
 */
public class Card extends ACard {
	/**
	 * This method create a new card object
	 * 
	 * @param color
	 * @param form
	 * @param panelFilling
	 * @param anzahl
	 */
	public Card(String color, String form, String panelFilling,
			int anzahl) {
		this.setColor(color);
		this.setFomr(form);
		this.setPanelFilling(panelFilling);
		this.setNumberOfComponents(anzahl);

	}

	/* (non-Javadoc)
	 * @see setgame.modell.impl.ICard#getColor()
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
		}else{
			this.color = null;
		}

	}

	/* (non-Javadoc)
	 * @see setgame.modell.impl.ICard#getFomr()
	 */
	@Override
	public String getFomr() {
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
		}else{
			this.form = null;
		}
	}

	/* (non-Javadoc)
	 * @see setgame.modell.impl.ICard#getPanelFilling()
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
		}else{
			this.panelFilling = null;
		}

	}

	/* (non-Javadoc)
	 * @see setgame.modell.impl.ICard#getNumberOfComponents()
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

		}else{
			this.anz = -1;
		}
	}
	/* (non-Javadoc)
	 * @see setgame.modell.impl.ICard#toString()
	 */
	@Override
	public String toString(){
		return this.color+" , "+this.form+" , "+this.panelFilling+" , "+this.anz;
	}
	/* (non-Javadoc)
	 * @see setgame.modell.impl.ICard#comparTo(setgame.modell.impl.Card)
	 */
	public boolean comparTo(Card card){
		if(this.color.equals(card.color) && this.anz == card.anz && this.form.equals(card.form) && this.panelFilling.equals(card.panelFilling)){
			return true;
		}
		return false;
		
	}




}
