package de.htwg.se.setgame.model;

/**
 * Created by David on 15.04.15.
 */
public interface IModelFactory {

    /**
     *
     * @return new IField
     */
    IField createField();

    /**
     *
     * @return new Pack
     */
    IPack createPack();

    /**
     *
     * @returnnew Game
     */
    IGame createGame();

    /**
     *
     * @return new Card
     */
    ICard createCard();

    /**
     *
     * @return new Player
     */
    IPlayer createPlayer();


}
