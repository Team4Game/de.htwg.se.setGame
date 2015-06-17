package de.htwg.se.setgame.controller.impl.service;

import de.htwg.se.setgame.model.ICard;

/**
 * Created by raina on 17.06.2015.
 */
public class SetService {

    public SetService(){

    }
    /**
     * @param stringOne color
     * @param stringTwo form
     * @param stringThree filling
     * @return
     */
    public boolean proveString(String stringOne, String stringTwo,
                                String stringThree) {
        if (stringOne.compareTo(stringTwo) == 0
                && stringOne.compareTo(stringThree) == 0
                && stringTwo.compareTo(stringThree) == 0) {
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
    public boolean proveNumberOfComponents(ICard cardOne, ICard cardTwo,
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
    public boolean proveForm(ICard cardOne, ICard cardTwo, ICard cardThree) {
        return proveString(cardOne.getForm(), cardTwo.getForm(),
                cardThree.getForm());
    }

    public boolean isASet(ICard cardOne, ICard cardTwo, ICard cardThree) {
        if(proveColor(cardOne, cardTwo, cardThree)
                && proveFilling(cardOne, cardTwo, cardThree)
                && proveNumberOfComponents(cardOne, cardTwo, cardThree)
                && proveForm(cardOne, cardTwo, cardThree)){
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
    private boolean proveFilling(ICard cardOne, ICard cardTwo, ICard cardThree) {
        return proveString(cardOne.getPanelFilling(),
                cardTwo.getPanelFilling(), cardThree.getPanelFilling());
    }
}
