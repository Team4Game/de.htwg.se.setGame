package de.htwg.se.setgame.controller.impl;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.IPack;
import de.htwg.se.setgame.util.observer.Event;
import de.htwg.se.setgame.util.observer.IObserver;

import java.util.List;
import java.util.Map;

/**
 * Created by raina on 17.06.2015.
 */
public class ControllerDummy implements IController {
    @Override
    public void isASetForController(ICard cardOne, ICard cardTwo, ICard cardThree, int player) {

    }

    @Override
    public List<ICard> getCardinGame() {
        return null;
    }

    @Override
    public IField getField() {
        return null;
    }

    @Override
    public List<ICard> getASetInGame() {
        return null;
    }

    @Override
    public boolean stillSetInGame() {
        return false;
    }

    @Override
    public List<ICard> getSetInField() {
        return null;
    }

    @Override
    public int getPlayerOnePoints() {
        return 0;
    }

    @Override
    public int getPlayerTwoPoints() {
        return 0;
    }

    @Override
    public int getPlayerOne() {
        return 0;
    }

    @Override
    public int getPlayerTwo() {
        return 0;
    }

    @Override
    public void newGame() {

    }

    @Override
    public void setFieldSize(int size) {

    }

    @Override
    public List<ICard> getCardInFieldGame() {
        return null;
    }

    @Override
    public Map<Integer, ICard> getCardsAndTheIndexOfCardInField() {
        return null;
    }

    @Override
    public IPack getPack() {
        return null;
    }

    @Override
    public List<ICard> getNewPack() {
        return null;
    }

    @Override
    public String saveGame(int player) {
        return null;
    }

    @Override
    public int loadGame(String uid) {
        return 0;
    }

    @Override
    public void setKIPlayer(String level) {

    }

    @Override
    public boolean isKIPLaying() {
        return false;
    }

    @Override
    public void addObserver(IObserver s) {

    }

    @Override
    public void removeObserver(IObserver s) {

    }

    @Override
    public void removeAllObservers() {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void notifyObservers(Event e) {

    }
}
