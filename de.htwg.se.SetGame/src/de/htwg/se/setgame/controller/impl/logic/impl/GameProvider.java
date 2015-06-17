package de.htwg.se.setgame.controller.impl.logic.impl;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPack;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;

import java.util.*;

/**
 * Created by raina on 03.06.2015.
 */
public class GameProvider {


    protected static final int INITIALVALUEOFFIELD = 12;
    private static final int MAX = 81;
    private static final int ONE = 1;
    private static final int NUMBERFORONELINE = 3;
    /**
     * Class Field.
     *
     * @author David Simon & Raina Bertolini
     * @date 7.12.201NUMBERFORONELINE
     * @category Modell
     */

    private int sizeOfField;
    private Map<Integer, Integer> ramdomListe;

    public IPack getiPack() {
        return iPack;
    }

    public void setiPack(IPack iPack) {
        this.iPack = iPack;
    }

    private IPack iPack;
    private IField field;
    private IModelFactory modelFactory;
    /**
     * startup of the objects
     */
    public GameProvider(IModelFactory modelFactory, int fieldsize) {
        PackProvider packProvider = new PackProvider(modelFactory);
        this.modelFactory =modelFactory;
        this.sizeOfField = fieldsize;
        this.ramdomListe = new TreeMap<Integer, Integer>();


        this.iPack = packProvider.getPack();
        this.field = modelFactory.createField();
    }

    public IField getField() {
        return field;
    }



    /**
     * pack for the game are the cards and the random positions
     */

    public void setField(IField field) {
        this.field = field;
    }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#startUp()
         */

    public void startUp() {
            Map<Integer, ICard> mapForGame = new TreeMap<Integer, ICard>();
            rand();
            int i = 0;
            for (ICard card : iPack.getPack()) {
                mapForGame.put(this.getRamdomListe().get(i), card);
                i++;
            }
            this.iPack.getPack().clear();
        this.iPack.getPack().addAll(mapForGame.values());
        startUpOfField();
        }

    /**
     * Filled array with integer number.
         *
         * @return filled array
     */
    public Map<Integer, Integer> rand() {
            int[] tem = new int[MAX];
            boolean b;
            for (int key = 0; key < MAX; key++) {
                b = true;
                int element = (int) (Math.random() * MAX + ONE);
                for (int t = 0; t < MAX; t++) {
                    if (element == tem[t] && key > 0) {
                        key = key - 1;
                        b = false;
                    }
                }
                if (b) {

                    this.getRamdomListe().put(key, element);

                    tem[key] = element;
                }

            }
            return getRamdomListe();

        }

    /**
     * Filled field with cards from the class Card
         *
         * @return filled Field with Cards
     */
    private void startUpOfField() {

            for (int index = 0; index < sizeOfField; index++) {
                field.getCardsInField().put(index, ((LinkedList<ICard>)this.iPack.getPack()).get(index));

            }

        this.iPack.getPack().removeAll(field.getCardsInField().values());
    }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#foundSet(de.htwg.se.setgame.modell.Card,
         * de.htwg.se.setgame.modell.Card, de.htwg.se.setgame.modell.Card)
         */

    public void foundSet(ICard cardOne, ICard cardTwo, ICard cardThree) {

            TreeSet<Integer> keyCardInField = new TreeSet<Integer>();
            for (Integer key : this.getCardInFieldGame().keySet()) {
                if (this.getCardInFieldGame().get(key).compareTo(cardOne)
                        || this.getCardInFieldGame().get(key).compareTo(cardTwo)
                        || this.getCardInFieldGame().get(key).compareTo(cardThree)) {
                    keyCardInField.add(key);
                    this.iPack.getPack().remove(this.getCardInFieldGame().get(key));
                }
            }
            for (Integer key : keyCardInField) {
                this.getCardInFieldGame().remove(key);

            }
            fillField();

        }

    /*
    *
    * cardOnStack are card that are not in game
    */
    private void fillField() {
            LinkedList<ICard> cardsOnStack = new LinkedList<ICard>();
        cardsOnStack.addAll(iPack.getPack());

        for (int index = 0; index < sizeOfField; index++) {
                if (this.getCardInFieldGame().get(index) == null
                        && !(cardsOnStack.isEmpty())) {
                    this.getCardInFieldGame().put(index,
                            cardsOnStack.getFirst());
                    iPack.getPack().remove(cardsOnStack.getFirst());
                    cardsOnStack.removeFirst();
                } else if (this.getCardInFieldGame().get(index) == null
                        && cardsOnStack.isEmpty()) {
                    this.getCardInFieldGame().remove(index);

                }
            }

        }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#getCardsInField()
         */

    public List<ICard> getCardsInField() {

            List<ICard> liste = new LinkedList<ICard>();
            liste.addAll(this.getCardInFieldGame().values());
            return liste;

        }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#setSizeOfField(int, java.util.List)
         */

    public void setSizeOfField(int size) {

            if(size != sizeOfField && size >= NUMBERFORONELINE && size <= getUnusedCards().size()){
                this.field.getCardsInField().clear();
                this.sizeOfField = size;
                fillField();
            }
        }


    public boolean remove(ICard card) {
            return false;
        }


    public boolean add(ICard card) {
            return false;
        }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#changeCards(java.util.List)
         */

    public void changeCards(List<ICard> liste) {
            for(int i = 0; i < liste.size(); i++){
                if(!field.getCardsInField().containsValue(liste.get(i))) {
                    if(field.getCardsInField().get(i)!= null){
                        iPack.getPack().add(field.getCardsInField().get(i));
                    }
                    field.getCardsInField().put(i, liste.get(i));
                    iPack.getPack().remove(liste.get(i));
                }
            }
        }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#getUnusedCards()
         */

    public List<ICard> getUnusedCards() {
        return iPack.getPack();
    }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#getSizeofField()
         */

    public int getSizeofField() {
            return getCardInFieldGame().size();
        }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#getAllCardsInGame()
         */

    public List<ICard> getAllCardsInGame() {
        List<ICard> result = new LinkedList<ICard>();
        result.addAll(field.getCardsInField().values());
        result.addAll(iPack.getPack());

        return result;
    }

    /**
     * @return the ramdomListe
     */
    public Map<Integer, Integer> getRamdomListe() {
            return ramdomListe;
        }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#getCardInFieldGame()
         */

    public Map<Integer, ICard> getCardInFieldGame() {
            return field.getCardsInField();
        }


    public void clear() {
        iPack.getPack().clear();
        PackProvider packProvider = new PackProvider(modelFactory);
        iPack.setPack(packProvider.getPack().getPack());
        this.field.getCardsInField().clear();
            this.sizeOfField = INITIALVALUEOFFIELD;
            startUp();

        }




}


