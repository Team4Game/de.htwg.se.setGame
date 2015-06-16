package de.htwg.se.setgame.model;

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
    IGame createGame();
    ICard createCard();
    IPlayer createPlayer();


}
