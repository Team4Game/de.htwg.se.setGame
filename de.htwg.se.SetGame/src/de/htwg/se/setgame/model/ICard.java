package de.htwg.se.setgame.model;

import de.htwg.se.setgame.util.persistence.couchdb.PersistentCard;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

@JsonDeserialize(as= PersistentCard.class)
public interface ICard {
	 /**
	 * @return colors
	 */
	String getColor();


	 /**
	 * @return form
	 */
	String getForm();


	 /**
	 * @return filling
	 */
	String getPanelFilling();

	 /**
	 * @return number of components
	 */
	int getNumberOfComponents();

	 /**
	 * @return srintg methode
	 */
	String toString();

    void setForm(String form);
    void setPanelFilling(String panelFilling);
    void setNumberOfComponents(int numberOfComponents);
    void setColor(String color);
    /**
	 * @param card 
	 * @return true if card is the same
	 */
	boolean compareTo(ICard card);

}