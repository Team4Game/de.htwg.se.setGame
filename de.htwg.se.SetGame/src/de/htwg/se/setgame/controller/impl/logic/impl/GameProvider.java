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
    private static final int LEGHTFORSTRING = CardAtributen.FILL[0].length();
    /**
     * Class Field.
     *
     * @author David Simon & Raina Bertolini
     * @date 7.12.201NUMBERFORONELINE
     * @category Modell
     */

    private int sizeOfField;
    private Set<Integer> listeofcontains;
    private Map<Integer, Integer> ramdomListe;

    private IPack iPack;
    private IField field;
    /*All Cards, like in field and in pack*/
    private List<ICard> packForGame;
    public IField getField() {
        return field;
    }

    public void setField(IField field) {
        this.field = field;
    }



    /**
     * pack for the game are the cards and the random positions
     */


    /**
     * startup of the objects
     */
    public GameProvider(IModelFactory modelFactory, int fieldsize) {
        PackProvider packProvider = new PackProvider(modelFactory);
        this.sizeOfField = fieldsize;
            this.ramdomListe = new TreeMap<Integer, Integer>();
            this.packForGame = new LinkedList<ICard>();
            this.listeofcontains = new TreeSet<Integer>();
        this.iPack = packProvider.getPack();
        this.field = modelFactory.createField();
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
            this.packForGame.addAll(mapForGame.values());
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
                this.getCardInFieldGame().put(index, this.packForGame.get(index));

            }

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
                    this.packForGame.remove(this.getCardInFieldGame().get(key));
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
            cardsOnStack.addAll(getUnusedCards());

            for (int index = 0; index < sizeOfField; index++) {
                if (this.getCardInFieldGame().get(index) == null
                        && !(cardsOnStack.isEmpty())) {
                    this.getCardInFieldGame().put(index,
                            cardsOnStack.getFirst());
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
                    field.getCardsInField().put(i, liste.get(i));
                }
            }
        }

        /*
         * (non-Javadoc)
         *
         * @see de.htwg.se.setgame.modell.IField#getUnusedCards()
         */

    public List<ICard> getUnusedCards() {
            LinkedList<ICard> list = new LinkedList<ICard>();
            list.addAll(this.packForGame);
            list.removeAll(this.getCardInFieldGame().values());
            return list;
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
            return this.packForGame;
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
            packForGame.clear();
            this.field.getCardsInField().clear();
            this.packForGame.clear();
            this.sizeOfField = INITIALVALUEOFFIELD;
            startUp();

        }

        @Override
        public String toString() {

            return returnString();
        }

        private String returnApeend() {
            return "|  ";
        }

        private String returnString() {
            this.listeofcontains.clear();
            StringBuilder field = new StringBuilder();
            field.append("\n");
            int t = 0;
            while (t != this.sizeOfField) {
                field.append(apendKeyInString()).append("\n");
                field.append(appendColorInString()).append("\n");
                field.append(appendFormInString()).append("\n");
                field.append(appendNumberOfComponents()).append("\n");
                field.append(appendFillingInString()).append("\n");
                t++;
                if (sizeOfField == listeofcontains.size()) {
                    break;
                }
            }
            return field.toString();
        }

        private String apendKeyInString() {
            StringBuilder fieldSt = new StringBuilder();
            int i = 0;
            for (Integer key : field.getCardsInField().keySet()) {
                if (!listeofcontains.contains(key)) {
                    double fehlt = LEGHTFORSTRING - 1;
                    double me = fehlt / 2;
                    fehlt = fehlt - me;
                    for (int loop = 0; loop < me; loop++) {
                        fieldSt.append(" ");
                    }
                    fieldSt.append("[" + key + "]");
                    for (int loop = 0; loop < fehlt; loop++) {
                        fieldSt.append(" ");
                    }
                    i++;
                    if (i == NUMBERFORONELINE) {
                        i = 0;
                        break;
                    }
                }
            }
            return fieldSt.toString();
        }

        private String appendColorInString() {
            int i = 0;
            StringBuilder fieldSt = new StringBuilder();
            for (Integer key : field.getCardsInField().keySet()) {
                if (!listeofcontains.contains(key)) {
                    int fehlt = LEGHTFORSTRING
                            - field.getCardsInField().get(key).getColor().toCharArray().length;
                    int me = fehlt / 2;
                    fehlt = fehlt - me;
                    fieldSt.append("|");
                    for (int loop = 0; loop < me; loop++) {
                        fieldSt.append(" ");
                    }
                    fieldSt.append(field.getCardsInField().get(key).getColor());
                    for (int loop = 0; loop < fehlt; loop++) {
                        fieldSt.append(" ");
                    }
                    fieldSt.append(returnApeend());
                    i++;
                    if (i == NUMBERFORONELINE) {
                        i = 0;
                        break;
                    }
                }
            }
            return fieldSt.toString();
        }

        private String appendFormInString() {
            int i = 0;
            StringBuilder fieldSt = new StringBuilder();
            for (Integer key : field.getCardsInField().keySet()) {
                if (!listeofcontains.contains(key)) {
                    int fehlt = LEGHTFORSTRING
                            - field.getCardsInField().get(key).getForm().toCharArray().length;
                    int me = fehlt / 2;
                    fehlt = fehlt - me;
                    fieldSt.append("|");
                    for (int loop = 0; loop < me; loop++) {
                        fieldSt.append(" ");
                    }
                    fieldSt.append(field.getCardsInField().get(key).getForm());
                    for (int loop = 0; loop < fehlt; loop++) {
                        fieldSt.append(" ");
                    }
                    fieldSt.append(returnApeend());
                    i++;
                    if (i == NUMBERFORONELINE) {
                        i = 0;
                        break;
                    }
                }
            }
            return fieldSt.toString();
        }

        private String appendNumberOfComponents() {
            int i = 0;
            StringBuilder fieldSt = new StringBuilder();
            for (Integer key : field.getCardsInField().keySet()) {
                if (!listeofcontains.contains(key)) {
                    int fehlt = LEGHTFORSTRING - 1;
                    int me = fehlt / 2;
                    fehlt = fehlt - me;
                    fieldSt.append("|");
                    for (int loop = 0; loop < me; loop++) {
                        fieldSt.append(" ");
                    }
                    fieldSt.append(field.getCardsInField().get(key).getNumberOfComponents());
                    for (int loop = 0; loop < fehlt; loop++) {
                        fieldSt.append(" ");
                    }
                    fieldSt.append(returnApeend());
                    i++;
                    if (i == NUMBERFORONELINE) {
                        i = 0;
                        break;
                    }
                }

            }
            return fieldSt.toString();
        }

        private String appendFillingInString() {
            int i = 0;
            StringBuilder fieldST = new StringBuilder();
            for (Integer key : field.getCardsInField().keySet()) {
                if (!listeofcontains.contains(key)) {
                    int fehlt = LEGHTFORSTRING
                            - field.getCardsInField().get(key).getPanelFilling()
                            .toCharArray().length;
                    int me = fehlt / 2;
                    fehlt = fehlt - me;
                    fieldST.append("|");
                    for (int loop = 0; loop < me; loop++) {
                        fieldST.append(" ");
                    }
                    fieldST.append(field.getCardsInField().get(key).getPanelFilling());
                    for (int loop = 0; loop < fehlt; loop++) {
                        fieldST.append(" ");
                    }
                    fieldST.append(returnApeend());
                    listeofcontains.add(key);
                    i++;
                    if (i == NUMBERFORONELINE) {
                        i = 0;
                        break;
                    }
                }
            }
            return fieldST.toString();
        }


}


