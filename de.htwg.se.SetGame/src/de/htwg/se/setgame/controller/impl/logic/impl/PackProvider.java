package de.htwg.se.setgame.controller.impl.logic.impl;


import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raina on 03.06.2015.
 */
public class PackProvider {


    public static final String[] FORME = {"ovally", "wave", "balk"};
    public static final String[] COLORS = {"red", "green", "purple"};
    public static final String[] FILL = {"halffill", "fill", "empty"};
    public static final int[] NUMBEROFCOMPONET = {1, 2, 3};
    private static final int SIZEOFARRAY = 81;
    private static final int NUMBEROFEACHCARD = 3;


    /*Instance variable*/
    private IModelFactory modelFactory;
    private IPack pack;
    private int colorIndex = 0;
    private int formeIndex = 0;
    private int fillgingIndex = 0;
    private int numbersIdex = 0;

    /**
     * Construct for card
     */

    public PackProvider(IModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        this.pack = modelFactory.createPack();
        List<ICard> cards = new LinkedList<ICard>();
        cards = Arrays.asList(creatCards());
        if (cards != null) {
            this.pack.setPack(cards);
        }
    }

    /**
     * @return the finish pack of the Game
     */
    private ICard[] creatCards() {
        ICard list[] = new ICard[SIZEOFARRAY];
        for (int i = 0; i < SIZEOFARRAY; i++) {

            list[i] = modelFactory.createCard();
            list[i].setColor(COLORS[colorIndex]);
            list[i].setForm(FORME[formeIndex]);
            list[i].setPanelFilling(FILL[fillgingIndex]);
            list[i].setPanelFilling(FILL[fillgingIndex]);
            list[i].setNumberOfComponents(NUMBEROFCOMPONET[numbersIdex]);
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
    public IPack getPack(){
        return this.pack;
    }
    /**
     * @return pack of cards
     */

    public List<ICard> getCardsInpack() {
        List<ICard> liste = new LinkedList<ICard>();
        for (ICard card : this.pack.getPack()) {
            liste.add(card);
        }
        return liste;
    }
}







