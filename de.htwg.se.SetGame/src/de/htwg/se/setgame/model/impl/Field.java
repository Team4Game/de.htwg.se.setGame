package de.htwg.se.setgame.model.impl;

import java.util.*;

import de.htwg.se.setgame.model.AField;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IPack;

/**
 * Class Field.
 * 
 * @author David Simon & Raina Bertolini
 * @date 7.12.201NUMBERFORONELINE
 * @category Modell
 */
public class Field extends AField {
    private Map<Integer, ICard>  cardsInField;
    public Field(){
        this.cardsInField = new HashMap<Integer, ICard>();
    }


    @Override
    public void setCardInField(Map<Integer, ICard> cardsInField) {

    }

    @Override
    public Map<Integer, ICard> getCardsInField() {
        return null;
    }
}
