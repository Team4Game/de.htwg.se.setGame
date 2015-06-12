package de.htwg.se.setgame.model;

import de.htwg.se.setgame.util.persistence.IGameDao;

import java.util.List;

/**
 * Created by David on 15.04.15.
 */
public interface IModelFactory {

    /**
     *
     * @return new IField
     */
    public IField createField();

    /**
     *
     * @return new IField
     */
    IPack createPack();
    IGameDao createGameDao();
    IGame createGame();
    ICard createCard();
    IPlayer createPlayer();

}
