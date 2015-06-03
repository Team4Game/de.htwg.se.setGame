package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPack;
/**
 * Created by David on 15.04.15.
 */
public class ModelFactory implements IModelFactory{

    public static final int FIELD_SIZE = 12;

    @Override
    public IField createField() {
        return new Field(createPack(), FIELD_SIZE);
    }

    @Override
    public IPack createPack() {
        return new Pack();
    }


}
