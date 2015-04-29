package de.htwg.se.setgame.aview.tui;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.se.setgame.controller.IController;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.util.observer.Event;
import de.htwg.se.setgame.util.observer.IObserver;

/**
 * @author raina
 *
 */
public class TextUI implements IObserver {

    public static final String MESSAGE_WELCOME = "Welcome to SetGame!!!! %nWell ist not that hard to play ;)%nIf you found a set please write : set PlayerOne (or PlayerTwo) and the number of the fields 1 2 3 (0-11)%nif you need a tipp plese write h%nif you want to know the solution please write s%nIf you want to know the game point please write GetPoints%nif you want to solve the set with out knowing just write solve%nSet size of the field 3-80 size number%nFor a newGame please write nw%nHave fun!!! if you want to finish the game please write exit";

    private IController controller;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private String newLine = System.getProperty("line.separator");

	private Logger logger = Logger.getLogger("de.htwg.se.de.htwg.se.setgame.aview.tui");

	/**
	 * @param controller
	 */
	@Inject
	public TextUI(IController controller) {
		this.controller = controller;
		controller.addObserver(this);

	}

	/* (non-Javadoc)
	 * @see de.htwg.se.de.htwg.se.setgame.util.observer.IObserver#update(de.htwg.se.de.htwg.se.setgame.util.observer.Event)
	 */
	@Override
	public void update(Event e) {
		printTUI();
	}

	/**
	 * @param stringOne
	 * @param stringTwo
	 * @param stringTree
	 * @param player
	 */
	private void setIn(String stringOne, String stringTwo, String stringTree, String player) {
		Integer[] arrayForSerNumber = new Integer[THREE];
		arrayForSerNumber[ZERO] = Integer.parseInt(stringOne);
		arrayForSerNumber[ONE] = Integer.parseInt(stringTwo);
		arrayForSerNumber[TWO] = Integer.parseInt(stringTree);
		boolean b = true;

		for (int index1 = 0; index1 < arrayForSerNumber.length; index1++) {
			if (arrayForSerNumber[index1] < ZERO || arrayForSerNumber[index1] > controller.getCardInFieldGame().size()) {
				b = false;
				logger.info(this.newLine + "wrong number please number between 0-11");
				break;
			}

		}
		if (b) {
			printASet(arrayForSerNumber, player);
		}

	}

	/**
	 * 
	 */
	private void lastMessage() {
		logger.info("Hey dude! there is no longger stes in game for you here is the Points ;) "
				+ this.newLine
				+ "Player 1 = "
				+ controller.getPlayerOnePoints()
				+ this.newLine
				+ "Player Two " + controller.getPlayerTwoPoints());
		if (controller.getPlayerTwoPoints() < controller.getPlayerOnePoints()) {
			logger.info("Gongratilations player one Dude you are amazing!!");
		} else if (controller.getPlayerTwoPoints() > controller
				.getPlayerOnePoints()) {
			logger.info("Congratilations player Two Dude you are amazing!!");
		} else {
			logger.info("nobody wins nobody pays the dinne xD");
		}

	}

	/**
	 * @param line
	 * @return
	 */
	public boolean processInputLine(String line) {
		logger.info(newLine + controller.getCardinGame().size());

		if (!controller.stillSetInGame() || controller.getCardinGame().isEmpty()) {
			lastMessage();
			return false;
		}

		boolean cont = true;
		String[] splintWords = line.split(" ");
		int index = ZERO;
		if (splintWords[index].compareTo("set") == ZERO) {
			if (compareIfPlayerIsRight(splintWords[ONE]) && splintWords.length > FOUR) {
				setIn(splintWords[TWO], splintWords[THREE], splintWords[FOUR], splintWords[ONE]);
			}

		} else if (splintWords[index].compareTo("GetPoints") == ZERO) {
			getPoints();
		} else if (splintWords[index].compareTo("h") == ZERO) {
			help();
		} else if (splintWords[index].compareTo("exit") == ZERO) {
			cont = false;
		} else if (splintWords[index].compareTo("size") == ZERO) {
			changeSize(splintWords[ONE]);
		}
		proveSomeStringsFromMet(splintWords[index]);

		return cont;

	}

	/**
	 * @param arrayForSerNumber
	 * @param string
	 */
	private void printASet(Integer[] arrayForSerNumber, String string) {
		int player = string.equalsIgnoreCase("PlayerOne") ? controller.getPlayerOne(): controller.getPlayerTwo();

		ICard cardOne = getCard(arrayForSerNumber, ZERO);
		ICard cardTwo = getCard(arrayForSerNumber, ONE);
		ICard cardThree = getCard(arrayForSerNumber, TWO);

		controller.isASetForController(cardOne, cardTwo, cardThree, player);
		logger.info(newLine + "Congratilations it is a SET!! ! size == "
				+ controller.getField().getAllCardsInGame().size());

	}

	private ICard getCard(Integer[] arrayForSerNumber, int i) {
		return controller.getField().getCardInFieldGame().get(arrayForSerNumber[i]);
	}

	/**
	 * @param string
	 * @return
	 */
	private boolean compareIfPlayerIsRight(String string) {
		return string.toLowerCase().equals("PlayerOne".toLowerCase()) || string.toLowerCase().equals("PlayerTwo".toLowerCase());
	}

	/**
	 * 
	 */
	public void printTUI() {
		logger.info(String.format(MESSAGE_WELCOME) + this.controller.getField().toString());
	}

	/**
	 * 
	 */
	private void solve() {
		if (this.controller.getSetInField().size() >= THREE) {
			logger.info("solved" + this.controller.getSetInField().toString()
					+ newLine
					+ controller.getField().getAllCardsInGame().size());
			this.controller.isASetForController(this.controller.getSetInField()
							.get(ZERO), this.controller.getSetInField().get(ONE),
					this.controller.getSetInField().get(TWO), THREE);
			printTUI();
		} else {
			logger.info("number of cards = "
					+ controller.getField().getAllCardsInGame().size());
			printTUI();

		}
	}

	/**
	 * 
	 */
	private void help() {
		logger.info("A tipp your set begiss with  " + this.newLine
				+ controller.getASetInGame().get(ZERO));

	}

	/**
	 * 
	 */
	private void solution() {
		for (ICard card : controller.getSetInField()) {
			for (Integer key : controller.getCardsAndTheIndexOfCardInField().keySet()) {
				if (controller.getCardsAndTheIndexOfCardInField().get(key)
						.comparTo(card)) {
					logger.info("Index  == " + key);
				}

			}
			logger.info(newLine);

		}
	}

	/**
	 * 
	 */
	private void newGame() {
		this.controller.newGame();

	}

	/**
	 * 
	 */
	private void getPoints() {
		logger.info(this.newLine + "Player one = "
				+ controller.getPlayerOnePoints() + this.newLine
				+ "Player Two = " + controller.getPlayerTwoPoints()
				+ this.newLine);

	}

	/**
	 * @param string
	 */
	private void changeSize(String string) {
		Integer i = Integer.parseInt(string);
		this.controller.setFieldSize(i);
		printTUI();

	}

	/**
	 * @param string
	 */
	private void proveSomeStringsFromMet(String string) {
		if (string.compareTo("solve") == ZERO) {
			solve();
		} else if (string.compareTo("s") == ZERO) {
			solution();

		} else if (string.compareTo("nw") == ZERO) {
			newGame();
		}

	}
}