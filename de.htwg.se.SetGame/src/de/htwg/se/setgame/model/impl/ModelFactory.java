package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.*;
import de.htwg.se.setgame.util.persistence.IGameDao;
import de.htwg.se.setgame.util.persistence.couchdb.GameDao;

import java.util.LinkedList;
import java.util.List;

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
    public IGameDao createGameDao() {
        return new GameDao();
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
