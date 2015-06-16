package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.AField;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class Field.
 * 
 * @author David Simon & Raina Bertolini
 * @date 7.12.201NUMBERFORONELINE
 * @category Modell
 */
public class Field extends AField {
    protected static final int INITIALVALUEOFFIELD = 12;
    private static final int MAX = 81;
    private static final int ONE = 1;
    private static final int NUMBERFORONELINE = 3;
    private static final int LEGHTFORSTRING = CardAtributen.FILL[0].length();
    private Map<Integer, ICard> cardsInField;
    private Set<Integer> listeofcontains;

    public Field() {
        this.cardsInField = new HashMap<Integer, ICard>();
        this.listeofcontains = new TreeSet<Integer>();
    }


    @Override
    public void setCardInField(Map<Integer, ICard> cardsInField) {
        this.cardsInField =cardsInField;

    }

    @Override
    public Map<Integer, ICard> getCardsInField() {
        return cardsInField;
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
        StringBuilder fieldSB = new StringBuilder();
        fieldSB.append("\n");
        int t = 0;
        while (t != cardsInField.size()) {
            fieldSB.append(apendKeyInString()).append("\n");
            fieldSB.append(appendColorInString()).append("\n");
            fieldSB.append(appendFormInString()).append("\n");
            fieldSB.append(appendNumberOfComponents()).append("\n");
            fieldSB.append(appendFillingInString()).append("\n");
            t++;
            if (cardsInField.size() == listeofcontains.size()) {
                break;
            }
        }
        return fieldSB.toString();
    }

    private String apendKeyInString() {
        StringBuilder fieldSB = new StringBuilder();
        int i = 0;
        for (Integer key : cardsInField.keySet()) {
            if (!listeofcontains.contains(key)) {
                double fehlt = LEGHTFORSTRING - 1;
                double me = fehlt / 2;
                fehlt = fehlt - me;
                for (int loop = 0; loop < me; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append("[" + key + "]");
                for (int loop = 0; loop < fehlt; loop++) {
                    fieldSB.append(" ");
                }
                i++;
                if (i == NUMBERFORONELINE) {
                    i = 0;
                    break;
                }
            }
        }
        return fieldSB.toString();
    }

    private String appendColorInString() {
        int i = 0;
        StringBuilder fieldSB = new StringBuilder();
        for (Integer key : cardsInField.keySet()) {
            if (!listeofcontains.contains(key)) {
                int fehlt = LEGHTFORSTRING
                        - cardsInField.get(key).getColor().toCharArray().length;
                int me = fehlt / 2;
                fehlt = fehlt - me;
                fieldSB.append("|");
                for (int loop = 0; loop < me; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(cardsInField.get(key).getColor());
                for (int loop = 0; loop < fehlt; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(returnApeend());
                i++;
                if (i == NUMBERFORONELINE) {
                    i = 0;
                    break;
                }
            }
        }
        return fieldSB.toString();
    }

    private String appendFormInString() {
        int i = 0;
        StringBuilder fieldSB = new StringBuilder();
        for (Integer key :cardsInField.keySet()) {
            if (!listeofcontains.contains(key)) {
                int fehlt = LEGHTFORSTRING
                        - cardsInField.get(key).getForm().toCharArray().length;
                int me = fehlt / 2;
                fehlt = fehlt - me;
                fieldSB.append("|");
                for (int loop = 0; loop < me; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(cardsInField.get(key).getForm());
                for (int loop = 0; loop < fehlt; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(returnApeend());
                i++;
                if (i == NUMBERFORONELINE) {
                    i = 0;
                    break;
                }
            }
        }
        return fieldSB.toString();
    }

    private String appendNumberOfComponents() {
        int i = 0;
        StringBuilder fieldSB = new StringBuilder();
        for (Integer key : cardsInField.keySet()) {
            if (!listeofcontains.contains(key)) {
                int fehlt = LEGHTFORSTRING - 1;
                int me = fehlt / 2;
                fehlt = fehlt - me;
                fieldSB.append("|");
                for (int loop = 0; loop < me; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(cardsInField.get(key).getNumberOfComponents());
                for (int loop = 0; loop < fehlt; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(returnApeend());
                i++;
                if (i == NUMBERFORONELINE) {
                    i = 0;
                    break;
                }
            }

        }
        return fieldSB.toString();
    }

    private String appendFillingInString() {
        int i = 0;
        StringBuilder fieldSB = new StringBuilder();
        for (Integer key : cardsInField.keySet()) {
            if (!listeofcontains.contains(key)) {
                int fehlt = LEGHTFORSTRING
                        - cardsInField.get(key).getPanelFilling()
                        .toCharArray().length;
                int me = fehlt / 2;
                fehlt = fehlt - me;
                fieldSB.append("|");
                for (int loop = 0; loop < me; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(cardsInField.get(key).getPanelFilling());
                for (int loop = 0; loop < fehlt; loop++) {
                    fieldSB.append(" ");
                }
                fieldSB.append(returnApeend());
                listeofcontains.add(key);
                i++;
                if (i == NUMBERFORONELINE) {
                    i = 0;
                    break;
                }
            }
        }
        return fieldSB.toString();
    }

}