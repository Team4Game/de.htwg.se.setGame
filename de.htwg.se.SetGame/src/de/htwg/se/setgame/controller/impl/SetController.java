package de.htwg.se.setgame.controller.impl;


import java.util.*;

import com.google.inject.Inject;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IPlugin;
import de.htwg.se.setgame.controller.impl.logic.impl.GameProvider;
import de.htwg.se.setgame.controller.impl.logic.impl.PackProvider;
import de.htwg.se.setgame.model.*;
import de.htwg.se.setgame.model.impl.Game;
import de.htwg.se.setgame.model.impl.Pack;
import de.htwg.se.setgame.model.impl.Player;
import de.htwg.se.setgame.util.observer.Observable;
import de.htwg.se.setgame.controller.impl.ki.KILevel;
import de.htwg.se.setgame.util.persistance.IGameDao;


/**
 * @author raina
 */
public class SetController extends Observable implements IController {

    public GameProvider getGameProvider() {
        return gameProvider;
    }

    public void setGameProvider(GameProvider gameProvider) {
        this.gameProvider = gameProvider;
    }


    private String token;
    /**
     * gameProvider
     */
    protected GameProvider gameProvider;

    /**
     * counter
     */
    private int counter;

    /**
     * number for set
     */
    private static final int NUMBEROFSETCARDS = 3;
    /**
     * numforgotrguth;
     */
    private static final int THREE = 3;
    /**
     * a number for
     */
    private static final int THOUSAND = 1000;
    /**
     *
     */
    private final int playerOne;
    /**
     *
     */
    private final int playerTwo;
    /**
     *
     */
    private int playerOneCounter;
    /**
     *
     */
    private int playerTwoCounter;
    /**
     *
     */
    private final Set<IPlugin> kiPlugins;


    private IGameDao gameDao;
    /**
     * Logic Construct make for the game a new field with a new pack!!!
     */
    @Inject
    public SetController(IModelFactory modelFactory, Set<IPlugin> kiPlugins, IGameDao gameDao) {
        this.kiPlugins = kiPlugins;
        this.gameProvider = new GameProvider(modelFactory, 12);
        this.counter = 0;
        this.gameProvider.startUp();
        this.playerOne = 1;
        this.playerTwo = 2;
        this.playerOneCounter = 0;
        this.playerTwoCounter = 0;
        this.gameDao = gameDao;
        checkIfIsASeTInGame();
    }

    @Override
    public void newGame() {
        this.gameProvider.clear();
        this.counter = 0;
        this.playerOneCounter = 0;
        this.playerTwoCounter = 0;
        this.token = null;
        notifyObservers();
    }
    

    /**
     *
     */
    protected void checkIfIsASeTInGame() {
        List<ICard> liste = new LinkedList<ICard>();
        liste.addAll(getSet(getCardInFieldGame()));
        if (liste.size() < NUMBEROFSETCARDS) {
            int i = 0;
            while (!changeCardsInGame() && i < THOUSAND) {
                i++;
            }

        }
        changeCardsInGame();
        notifyObservers();
    }


    /**
     * @param cardOne
     * @param cardTwo
     * @param cardThree
     * @return true if all the cards are in the field is only a safety Method
     */
    protected boolean isInField(ICard cardOne, ICard cardTwo, ICard cardThree) {
        this.counter = 0;
        for (ICard card : gameProvider.getCardsInField().values()) {
            if (card.compareTo(cardOne) || card.compareTo(cardTwo)
                    || card.compareTo(cardThree)) {
                counter++;
            }
        }
        if (this.counter == NUMBEROFSETCARDS) {
            return true;
        }
        return false;

    }

    /**
     * @param cardOne
     * @param cardTwo
     * @param cardThree
     * @return return true if is a set.
     */
    protected boolean isASet(ICard cardOne, ICard cardTwo, ICard cardThree) {

        if (!isInField(cardOne, cardTwo, cardThree)) {
            return false;
        } else {
            if (proveIfIsASet(cardOne, cardTwo, cardThree)) {
                gameProvider.foundSet(cardOne, cardTwo, cardThree);
                if (getASetInGame().size() >= THREE) {
                    return true;
                } else if (allTheSetsInField(this.gameProvider.getAllCardsInGame())) {
                    changeCardsInGame();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param list is the Cards in field the new ones if there is no set anymore.
     * @return true if still set in fields
     */
    protected boolean allTheSetsInField(List<ICard> list) {
        if (!getSet(list).isEmpty()) {
            return true;
        }
        if (changeCardsInGame()) {
            return true;
        }

        return false;

    }

    @Override
    public void setFieldSize(int size) {
        if (size > 0) {
            this.gameProvider.setSizeOfField(size);
        }
        checkIfIsASeTInGame();
    }

    /**
     * changed the Cards in the field if necessary. to
     * @return true if cards change
     */
    private boolean changeCardsInGame() {
        List<ICard> allCards = new LinkedList<ICard>();
        allCards.addAll(gameProvider.getUnusedCards());
        if (!allCards.isEmpty() && !getSet(allCards).isEmpty()) {
            gameProvider.changeCards(getSet(allCards));
            return true;
        }
        return false;

    }

    /**
     * @param cardOne
     * @param cardTwo
     * @param cardThree
     * @return
     */
    private boolean proveColor(ICard cardOne, ICard cardTwo, ICard cardThree) {
        return proveString(cardOne.getColor(), cardTwo.getColor(),
                cardThree.getColor());
    }

    /**
     * @param cardOne
     * @param cardTwo
     * @param cardThree
     * @return
     */
    private boolean proveNumberOfComponents(ICard cardOne, ICard cardTwo,
                                            ICard cardThree) {
        if (cardOne.getNumberOfComponents() == cardTwo.getNumberOfComponents()
                && cardOne.getNumberOfComponents() == cardThree
                .getNumberOfComponents()) {
            return true;
        } else if (cardOne.getNumberOfComponents() != cardTwo
                .getNumberOfComponents()
                && cardOne.getNumberOfComponents() != cardThree
                .getNumberOfComponents()
                && cardTwo.getNumberOfComponents() != cardThree
                .getNumberOfComponents()) {
            return true;
        }
        return false;
    }

    /**
     * @param cardOne
     * @param cardTwo
     * @param cardThree
     * @return
     */
    private boolean proveFilling(ICard cardOne, ICard cardTwo, ICard cardThree) {
        return proveString(cardOne.getPanelFilling(),
                cardTwo.getPanelFilling(), cardThree.getPanelFilling());
    }

    /**
     * @param cardOne
     * @param cardTwo
     * @param cardThree
     * @return
     */
    private boolean proveForm(ICard cardOne, ICard cardTwo, ICard cardThree) {
        return proveString(cardOne.getForm(), cardTwo.getForm(),
                cardThree.getForm());
    }

    /**
     * @param stringOne
     * @param stringTwo
     * @param stringThree
     * @return
     */
    private boolean proveString(String stringOne, String stringTwo,
                                String stringThree) {
        if (stringOne.compareTo(stringTwo) == 0
                && stringOne.compareTo(stringThree) == 0 && stringTwo.compareTo(stringThree) == 0) {
            return true;
        } else if (stringOne.compareTo(stringTwo) != 0
                && stringOne.compareTo(stringThree) != 0
                && stringTwo.compareTo(stringThree) != 0) {
            return true;
        }
        return false;
    }

    /**
     * @param cardOne
     * @param cardTwo
     * @param cardThree
     * @return
     */
    private boolean proveIfIsASet(ICard cardOne, ICard cardTwo, ICard cardThree) {
        if (proveColor(cardOne, cardTwo, cardThree)
                && proveFilling(cardOne, cardTwo, cardThree)
                && proveNumberOfComponents(cardOne, cardTwo, cardThree)
                && proveForm(cardOne, cardTwo, cardThree)) {
            return true;
        }
        return false;

    }

    /**
     * @param list
     * @return
     */
    private List<ICard> getSet(List<ICard> list) {
        LinkedList<ICard> setList = new LinkedList<ICard>();
        if (list.size() >= NUMBEROFSETCARDS) {

            for (ICard cardOne : list) {
                for (ICard cardTwo : list) {
                    if (!cardOne.equals(cardTwo)) {
                        for (ICard cardThree : list) {

                            if (proveIfIsASet(cardOne, cardTwo, cardThree)
                                    && !cardThree.equals(cardOne)
                                    && !(cardTwo.equals(cardThree))) {

                                setList.add(cardOne);
                                setList.add(cardTwo);
                                setList.add(cardThree);
                                return setList;
                            }
                        }
                    }
                }
            }

        }

        return setList;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getCardinGame()
     */
    @Override
    public List<ICard> getCardinGame() {
        return this.gameProvider.getAllCardsInGame();
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getField()
     */
    @Override
    public IField getField() {
        return  this.gameProvider.getField();
    }


    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#isASetForController(de.htwg.se.setgame.modell.impl.Card, de.htwg.se.setgame.modell.impl.Card, de.htwg.se.setgame.modell.impl.Card, int)
     */
    @Override
    public void isASetForController(ICard cardOne, ICard cardTwo,
                                    ICard cardThree, int player) {
        if (isASet(cardOne, cardTwo, cardThree)) {
            if (this.playerOne == player) {
                this.playerOneCounter = this.playerOneCounter + 1;
            } else if (this.playerTwo == player) {
                this.playerTwoCounter = this.playerTwoCounter + 1;

            }
            if (playerOne == player || player == this.playerTwo || player >= 0) {
                notifyObservers();
            }
        }
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getASetInGame()
     */
    @Override
    public List<ICard> getASetInGame() {
        return getSet(getCardInFieldGame());
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#stillSetInGame()
     */
    @Override
    public boolean stillSetInGame() {
        LinkedList<ICard> liste = new LinkedList<ICard>();
        liste.addAll(getSet(this.gameProvider.getAllCardsInGame()));
        if (liste.isEmpty()) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getSetInField()
     */
    @Override
    public List<ICard> getSetInField() {
        return getSet(getCardInFieldGame());

    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerOnePoints()
     */
    @Override
    public int getPlayerOnePoints() {
        return this.playerOneCounter;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerTwoPoints()
     */
    @Override
    public int getPlayerTwoPoints() {
        return this.playerTwoCounter;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerOne()
     */
    @Override
    public int getPlayerOne() {
        return this.playerOne;
    }

    /* (non-Javadoc)
     * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerTwo()
     */
    @Override
    public int getPlayerTwo() {
        return this.playerTwo;
    }

    @Override
    public List<ICard> getCardInFieldGame() {
        List<ICard> result = new LinkedList<ICard>();
        result.addAll(gameProvider.getCardInFieldGame().values());
        return result;
    }

    @Override
    public Map<Integer, ICard> getCardsAndTheIndexOfCardInField() {
        return this.gameProvider.getCardInFieldGame();
    }

    @Override
    public IPack getPack() {
        return gameProvider.getiPack();
    }
    @Override
    public void saveGame() {

        if(this.token == null){
            token = UUID.randomUUID().toString();
        }

        List<ICard> unusedCards = this.getPack().getPack();
        Map<Integer, ICard> cardsInField = this.gameProvider.getCardInFieldGame();
        IPlayer player1 = gameProvider.getModelFactory().createPlayer();
        player1.setCounter(playerOneCounter);
        player1.setId(playerOne);
        IPlayer player2 = gameProvider.getModelFactory().createPlayer();
        player2.setCounter(playerTwoCounter);
        player2.setId(playerTwo);
        IGame game = gameProvider.getModelFactory().createGame();
        game.setCardInField(cardsInField);
        game.setUnusedCards(unusedCards);
        game.setPlayerOne(player1);
        game.setPlayerTwo(player2);
        game.setGameToken(token);
        gameDao.createOrUpdate(game);
        System.out.println("TOKEN = "+ token);



    }

    @Override
    public void loadGame(String id) {
        IGame game = gameDao.findGame(id);
        if(game == null){

        } else {
            this.gameProvider.getCardInFieldGame().clear();
            this.gameProvider.getUnusedCards().clear();
            this.gameProvider.getCardsInField().clear();
            this.counter = 0;
            this.playerOneCounter = game.getPlayerOne().getCounter();
            this.playerTwoCounter = game.getPlayerTwo().getCounter();
            this.gameProvider.getField().setCardInField(game.getCardInField());
            this.gameProvider.getiPack().setPack(game.getUnusedCards());
            this.token = game.getToken();
            notifyObservers();
        }

    }

    @Override
    public void setKiPlayer(KILevel level){
        for(IPlugin plugin : kiPlugins){
            if(plugin.isKiRightLevel(level)){
                plugin.startKi(level);
            }else{
                plugin.stopKi(level);
            }

        }

    }

}
