package de.htwg.se.setgame.controller.impl;

import com.google.inject.Inject;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.IKiPlugin;
import de.htwg.se.setgame.controller.impl.logic.impl.GameProvider;
import de.htwg.se.setgame.controller.impl.logic.impl.PackProvider;
import de.htwg.se.setgame.controller.impl.service.KiService;
import de.htwg.se.setgame.controller.impl.service.LoadAndSaveGameService;
import de.htwg.se.setgame.controller.impl.service.SetService;
import de.htwg.se.setgame.model.*;
import de.htwg.se.setgame.util.observer.Observable;
import de.htwg.se.setgame.util.persistence.IGameDao;

import java.util.*;

/**
 * @author raina
 */
public class SetController extends Observable implements IController {

    private static final int NUMBEROFSETCARDS = 3;
    private static final int THREE = 3;
    private static final int THOUSAND = 1000;
    private static final int AMOUNT = 12;
    private final int playerOne;
    private final int playerTwo;
    private SetService setService;
    private GameProvider gameProvider;
    private int counter;
    private String uidForGame;
	private int playerOneCounter;
	private int playerTwoCounter;
    private IPack pack;
    private KiService kiService;
    private LoadAndSaveGameService loadAndSaveGameService;
    /**
     * Logic Construct make for the game a new field with a new pack!!!
     * start Aktoren
     */
    private final Set<IKiPlugin> plugin;
	@Inject
	public SetController(IModelFactory modelFactory, IGameDao gameDao , Set<IKiPlugin> plugin) {
		this.gameProvider = new GameProvider(modelFactory, AMOUNT);
		PackProvider packProvider = new PackProvider(modelFactory);
        this.pack = packProvider.getPack();
        this.counter = 0;
        this.plugin = plugin;
		this.gameProvider.startUp();
		this.playerOne = 1;
		this.playerTwo = 2;
		this.playerOneCounter = 0;
		this.playerTwoCounter = 0;
        this.kiService = new KiService(this);
        this.setService = new SetService();
        loadAndSaveGameService = new LoadAndSaveGameService(gameDao, modelFactory);
        checkIfIsASeTInGame();
    }

    @Override
    public void newGame() {
        this.gameProvider.clear();
		this.counter = 0;
		this.playerOneCounter = 0;
		this.playerTwoCounter = 0;
		notifyObservers();
	}

	/**
     *
     */
	private void checkIfIsASeTInGame() {
		List<ICard> liste = new LinkedList<ICard>();
		liste.addAll(getSet(this.gameProvider.getCardsInField()));
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
		for (ICard card : gameProvider.getCardsInField()) {
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
				} else if (allTheSetsInField(this.gameProvider
						.getAllCardsInGame())) {
					changeCardsInGame();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param list
	 *            is the Cards in field the new ones if there is no set anymore.
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
	 * 
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
	private boolean proveIfIsASet(ICard cardOne, ICard cardTwo, ICard cardThree) {
        if (setService.isASet(cardOne, cardTwo, cardThree)) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getCardinGame()
	 */
	@Override
	public List<ICard> getCardinGame() {
		return this.gameProvider.getAllCardsInGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getField()
	 */
	@Override
	public IField getField() {
		return this.gameProvider.getField();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.se.setgame.controller.impl.ISuperController#isASetForController
	 * (de.htwg.se.setgame.modell.impl.Card,
	 * de.htwg.se.setgame.modell.impl.Card, de.htwg.se.setgame.modell.impl.Card,
	 * int)
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
				if(kiService.isKiPlaying() && player != playerTwo){
                    kiService.notifyKi();
                }
                notifyObservers();

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getASetInGame()
	 */
	@Override
	public List<ICard> getASetInGame() {
		return getSet(this.gameProvider.getCardsInField());
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getSetInField()
	 */
	@Override
	public List<ICard> getSetInField() {
		return getSet(this.gameProvider.getCardsInField());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.se.setgame.controller.impl.ISuperController#getPlayerOnePoints()
	 */
	@Override
	public int getPlayerOnePoints() {
		return this.playerOneCounter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.se.setgame.controller.impl.ISuperController#getPlayerTwoPoints()
	 */
	@Override
	public int getPlayerTwoPoints() {
		return this.playerTwoCounter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerOne()
	 */
	@Override
	public int getPlayerOne() {
		return this.playerOne;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.controller.impl.ISuperController#getPlayerTwo()
	 */
	@Override
	public int getPlayerTwo() {
		return this.playerTwo;
	}

	@Override
	public List<ICard> getCardInFieldGame() {
		return this.gameProvider.getCardsInField();
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
    public List<ICard> getNewPack() {
       return this.pack.getPack();
    }
    @Override
	public String saveGame(int playerNumber) {
        String uid;
        if (this.uidForGame == null) {
            // generate unique id
            uid = UUID.randomUUID().toString();
        }else{
            uid = uidForGame;
        }
        String newUid = loadAndSaveGameService.saveGame(uid, this.playerOneCounter, this.playerTwoCounter, this.counter,
                getPack().getPack(), getField().getCardsInField(), this.playerOne, this.playerTwo);

        return newUid + "+" + playerNumber;

    }

    @Override
    public int loadGame(String uid) {

        IGame game = loadAndSaveGameService.getGame(uid);
        if (game == null) {
            // game not found
            return -1;
        }

        this.gameProvider.getCardInFieldGame().clear();
        this.gameProvider.getUnusedCards().clear();
        this.gameProvider.getAllCardsInGame().clear();
        this.counter = game.getCounter();
        this.playerOneCounter = game.getPlayerOne().getCounter();
        this.playerTwoCounter = game.getPlayerTwo().getCounter();
        this.getField().setCardInField(game.getCardsInField());
        this.getPack().setPack(game.getUnusedCards());
        notifyObservers();
        this.uidForGame = game.getId();
        return 0;

    }

    @Override
    public void setKIPlayer(String level) {
        for(IKiPlugin kiPlugin : plugin){
            if(kiPlugin.isKiLevel(level)){
                kiService.startKiPlugin(kiPlugin);
                break;
            }
        }

    }

    @Override
    public boolean isKIPLaying() {
        return kiService.isKiPlaying();
    }

}
