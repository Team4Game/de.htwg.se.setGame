package de.htwg.se.setgame.util.persistance.hibernate;

import de.htwg.se.setgame.model.ICard;

/**
 * Created by David on 04.01.16.
 */
public class Card implements ICard {
    @Override
    public String getColor() {
        return null;
    }

    @Override
    public String getForm() {
        return null;
    }

    @Override
    public String getPanelFilling() {
        return null;
    }

    @Override
    public int getNumberOfComponents() {
        return 0;
    }

    @Override
    public void setForm(String form) {

    }

    @Override
    public void setPanelFilling(String panelFilling) {

    }

    @Override
    public void setNumberOfComponents(int numberOfComponents) {

    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public boolean compareTo(ICard card) {
        return false;
    }
}
