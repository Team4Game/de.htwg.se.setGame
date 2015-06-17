package de.htwg.se.setgame.controller.impl;

import com.google.inject.Inject;
import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.controller.impl.logic.impl.GameProvider;
import de.htwg.se.setgame.controller.impl.logic.impl.PackProvider;
import de.htwg.se.setgame.model.*;
import de.htwg.se.setgame.util.observer.Observable;
import de.htwg.se.setgame.util.persistence.IGameDao;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private GameProvider gameProvider;
    private int counter;
    private String uidForGame;
	private int playerOneCounter;
	private int playerTwoCounter;
    private IModelFactory modelFactory;
    private IPack pack;
	/**
	 * Logic Construct make for the game a new field with a new pack!!!
	 */
    private IGameDao gameDao;
	@Inject
	public SetController(IModelFactory modelFactory, IGameDao gameDao) {
		this.gameProvider = new GameProvider(modelFactory, AMOUNT);
		PackProvider packProvider = new PackProvider(modelFactory);
        this.pack = packProvider.getPack();
        this.counter = 0;
        this.gameDao = gameDao;
        this.modelFactory =  modelFactory;
		this.gameProvider.startUp();
		this.playerOne = 1;
		this.playerTwo = 2;
		this.playerOneCounter = 0;
		this.playerTwoCounter = 0;
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
				&& stringOne.compareTo(stringThree) == 0
				&& stringTwo.compareTo(stringThree) == 0) {
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

		IPlayer playerOne = modelFactory.createPlayer();
        playerOne.setCounter(this.playerOneCounter);
        playerOne.setPid(this.playerOne);
		IPlayer playerTwo = modelFactory.createPlayer();
        playerTwo.setCounter(this.playerTwoCounter);
        playerTwo.setPid(this.playerTwo);
		int counter = this.counter;
		Map<Integer, ICard> cardsInField = this.getField().getCardsInField();
		List<ICard> unusedCards = this.getPack().getPack();
        String uid;
		if(this.uidForGame == null) {
            // generate unique id
         uid   =UUID.randomUUID().toString();
        }else{
            uid = uidForGame;
        }
        IGame game = modelFactory.createGame();
        game.setId(uid);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        game.setCounter(counter);
        game.setCardsInField(cardsInField);
        game.setUnusedCards(unusedCards);
		IGameDao dao = this.gameDao;

        dao.createOrUpdateGame(game);
		
		System.out.println(game.getId());
		
		dao.closeDb();
		this.uidForGame = game.getId();
		return game.getId()+"+"+playerNumber;

	}

	@Override
	public int loadGame(String uid) {
		
		// sample savegame:
		// 28395449-a76e-4499-8189-f7061e3994b7;

		IGameDao dao = this.gameDao;
		 IGame game = dao.findGame(uid);
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
		
		dao.closeDb();
		this.uidForGame = game.getId();
		return 0;

	}

    @Override
    public void setKIPlayer(String level) {

    }

}
