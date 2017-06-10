package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.*;


/**
 * Created by David on 15.04.15.
 */
public class ModelFactory implements IModelFactory {

    public static final int FIELD_SIZE = 12;

    @Override
    public IField createField() {
        return new Field();
    }

    @Override
    public IPack createPack() {
        return new Pack();
    }



    @Override
    public IGame createGame() {
        return new Game();
    }

    @Override
    public ICard createCard() {
        return new Card();
    }

    @Override
    public IPlayer createPlayer() {
        return new Player();
    }




}
