package de.htwg.se.setgame.model.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import de.htwg.se.setgame.model.AField;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IPack;

/**
 * Class Field.
 * 
 * @author David Simon & Raina Bertolini
 * @date 7.12.201NUMBERFORONELINE
 * @category Modell
 */
public class Field extends AField {
	private int sizeOfField;
	protected static final int INITIALVALUEOFFIELD = 12;
	private static final int MAX = 81;
	private static final int ONE = 1;
	private static final int NUMBERFORONELINE = 3;
	private Set<Integer> listeofcontains;
	private Map<Integer, Integer> ramdomListe;
	private static final int LEGHTFORSTRING = Pack.FILL[0].length();
	private Map<Integer, ICard> cardInFieldGame;
    private IPack iPack;
	/** 
	 * pack for the game are the cards and the random positions
	 */
	private List<ICard> packForGame;
	/**
	 * startup of the objects
	 */
	public Field(IPack pack, int fieldsize) {
		this.sizeOfField = fieldsize;
		this.cardInFieldGame = new TreeMap<Integer, ICard>();
		this.ramdomListe = new TreeMap<Integer, Integer>();
		this.packForGame = new LinkedList<ICard>();
		this.listeofcontains = new TreeSet<Integer>();
        this.iPack = pack;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#startUp()
	 */
	@Override
	public void startUp() {
		Map<Integer, ICard> mapForGame = new TreeMap<Integer, ICard>();
		rand();
		int i = 0;
		for (ICard card : iPack.getPack()) {
			mapForGame.put(this.getRamdomListe().get(i), card);
			i++;
		}
		this.packForGame.addAll(mapForGame.values());

		startUpOfField();
	}

	/**
	 * Filled array with integer number.
	 * 
	 * @return filled array
	 */
	public Map<Integer, Integer> rand() {
		int[] tem = new int[MAX];
		boolean b;
		for (int key = 0; key < MAX; key++) {
			b = true;
			int element = (int) (Math.random() * MAX + ONE);
			for (int t = 0; t < MAX; t++) {
				if (element == tem[t] && key > 0) {
					key = key - 1;
					b = false;
				}
			}
			if (b) {

				this.getRamdomListe().put(key, element);

				tem[key] = element;
			}

		}
		return getRamdomListe();

	}

	/**
	 * Filled field with cards from the class Card
	 * 
	 * @return filled Field with Cards
	 */
	private void startUpOfField() {

		for (int index = 0; index < sizeOfField; index++) {
			this.getCardInFieldGame().put(index, this.packForGame.get(index));

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#foundSet(de.htwg.se.setgame.modell.Card,
	 * de.htwg.se.setgame.modell.Card, de.htwg.se.setgame.modell.Card)
	 */
	@Override
	public void foundSet(ICard cardOne, ICard cardTwo, ICard cardThree) {

		TreeSet<Integer> keyCardInField = new TreeSet<Integer>();
		for (Integer key : this.getCardInFieldGame().keySet()) {
			if (this.getCardInFieldGame().get(key).compareTo(cardOne)
					|| this.getCardInFieldGame().get(key).compareTo(cardTwo)
					|| this.getCardInFieldGame().get(key).compareTo(cardThree)) {
				keyCardInField.add(key);
				this.packForGame.remove(this.getCardInFieldGame().get(key));
			}
		}
		for (Integer key : keyCardInField) {
			this.getCardInFieldGame().remove(key);

		}
		fillField();

	}
    /*
    *
    * cardOnStack are card that are not in game
    */
	private void fillField() {
		LinkedList<ICard> cardsOnStack = new LinkedList<ICard>();
		cardsOnStack.addAll(getUnusedCards());

		for (int index = 0; index < sizeOfField; index++) {
			if (this.getCardInFieldGame().get(index) == null
					&& !(cardsOnStack.isEmpty())) {
				this.getCardInFieldGame().put(index,
						cardsOnStack.getFirst());
				cardsOnStack.removeFirst();
			} else if (this.getCardInFieldGame().get(index) == null
					&& cardsOnStack.isEmpty()) {
				this.getCardInFieldGame().remove(index);

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#getCardsInField()
	 */
	@Override
	public List<ICard> getCardsInField() {

		List<ICard> liste = new LinkedList<ICard>();
		liste.addAll(this.getCardInFieldGame().values());
		return liste;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#setSizeOfField(int, java.util.List)
	 */
	@Override
	public void setSizeOfField(int size) {

        if(size != sizeOfField && size >= NUMBERFORONELINE && size <= getUnusedCards().size()){
            this.cardInFieldGame.clear();
            this.sizeOfField = size;
            fillField();
        }
	}

    @Override
    public boolean remove(ICard card) {
        return false;
    }

    @Override
    public boolean add(ICard card) {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see de.htwg.se.setgame.modell.IField#changeCards(java.util.List)
     */
	@Override
	public void changeCards(List<ICard> liste) {
		   for(int i = 0; i < liste.size(); i++){
              if(!this.cardInFieldGame.containsValue(liste.get(i))) {
                  this.cardInFieldGame.put(i, liste.get(i));
              }
           }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#getUnusedCards()
	 */
	@Override
	public List<ICard> getUnusedCards() {
		LinkedList<ICard> list = new LinkedList<ICard>();
		list.addAll(this.packForGame);
		list.removeAll(this.getCardInFieldGame().values());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#getSizeofField()
	 */
	@Override
	public int getSizeofField() {
		return getCardInFieldGame().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#getAllCardsInGame()
	 */
	@Override
	public List<ICard> getAllCardsInGame() {
		return this.packForGame;
	}

	/**
	 * @return the ramdomListe
	 */
	public Map<Integer, Integer> getRamdomListe() {
		return ramdomListe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.se.setgame.modell.IField#getCardInFieldGame()
	 */
	@Override
	public Map<Integer, ICard> getCardInFieldGame() {
		return cardInFieldGame;
	}

	@Override
	public void clear() {
		packForGame.clear();
		this.cardInFieldGame.clear();
		this.packForGame.clear();
		this.sizeOfField = INITIALVALUEOFFIELD;
		startUp();

	}

	@Override
	public String toString() {

		return returnString();
	}

	private String returnApeend() {
		return "|  ";
	}

	private String returnString() {
		this.listeofcontains.clear();
		StringBuilder field = new StringBuilder();
		field.append("\n");
		int t = 0;
		while (t != this.sizeOfField) {
			field.append(apendKeyInString()).append("\n");
			field.append(appendColorInString()).append("\n");
			field.append(appendFormInString()).append("\n");
			field.append(appendNumberOfComponents()).append("\n");
			field.append(appendFillingInString()).append("\n");
			t++;
			if (sizeOfField == listeofcontains.size()) {
				break;
			}
		}
		return field.toString();
	}

	private String apendKeyInString() {
		StringBuilder field = new StringBuilder();
		int i = 0;
		for (Integer key : cardInFieldGame.keySet()) {
			if (!listeofcontains.contains(key)) {
				double fehlt = LEGHTFORSTRING - 1;
				double me = fehlt / 2;
				fehlt = fehlt - me;
				for (int loop = 0; loop < me; loop++) {
					field.append(" ");
				}
				field.append("[" + key + "]");
				for (int loop = 0; loop < fehlt; loop++) {
					field.append(" ");
				}
				i++;
				if (i == NUMBERFORONELINE) {
					i = 0;
					break;
				}
			}
		}
		return field.toString();
	}

	private String appendColorInString() {
		int i = 0;
		StringBuilder field = new StringBuilder();
		for (Integer key : cardInFieldGame.keySet()) {
			if (!listeofcontains.contains(key)) {
				int fehlt = LEGHTFORSTRING
						- cardInFieldGame.get(key).getColor().toCharArray().length;
				int me = fehlt / 2;
				fehlt = fehlt - me;
				field.append("|");
				for (int loop = 0; loop < me; loop++) {
					field.append(" ");
				}
				field.append(cardInFieldGame.get(key).getColor());
				for (int loop = 0; loop < fehlt; loop++) {
					field.append(" ");
				}
				field.append(returnApeend());
				i++;
				if (i == NUMBERFORONELINE) {
					i = 0;
					break;
				}
			}
		}
		return field.toString();
	}

	private String appendFormInString() {
		int i = 0;
		StringBuilder field = new StringBuilder();
		for (Integer key : cardInFieldGame.keySet()) {
			if (!listeofcontains.contains(key)) {
				int fehlt = LEGHTFORSTRING
						- cardInFieldGame.get(key).getForm().toCharArray().length;
				int me = fehlt / 2;
				fehlt = fehlt - me;
				field.append("|");
				for (int loop = 0; loop < me; loop++) {
					field.append(" ");
				}
				field.append(cardInFieldGame.get(key).getForm());
				for (int loop = 0; loop < fehlt; loop++) {
					field.append(" ");
				}
				field.append(returnApeend());
				i++;
				if (i == NUMBERFORONELINE) {
					i = 0;
					break;
				}
			}
		}
		return field.toString();
	}

	private String appendNumberOfComponents() {
		int i = 0;
		StringBuilder field = new StringBuilder();
		for (Integer key : cardInFieldGame.keySet()) {
			if (!listeofcontains.contains(key)) {
				int fehlt = LEGHTFORSTRING - 1;
				int me = fehlt / 2;
				fehlt = fehlt - me;
				field.append("|");
				for (int loop = 0; loop < me; loop++) {
					field.append(" ");
				}
				field.append(cardInFieldGame.get(key).getNumberOfComponents());
				for (int loop = 0; loop < fehlt; loop++) {
					field.append(" ");
				}
				field.append(returnApeend());
				i++;
				if (i == NUMBERFORONELINE) {
					i = 0;
					break;
				}
			}

		}
		return field.toString();
	}

	private String appendFillingInString() {
		int i = 0;
		StringBuilder field = new StringBuilder();
		for (Integer key : cardInFieldGame.keySet()) {
			if (!listeofcontains.contains(key)) {
				int fehlt = LEGHTFORSTRING
						- cardInFieldGame.get(key).getPanelFilling()
								.toCharArray().length;
				int me = fehlt / 2;
				fehlt = fehlt - me;
				field.append("|");
				for (int loop = 0; loop < me; loop++) {
					field.append(" ");
				}
				field.append(cardInFieldGame.get(key).getPanelFilling());
				for (int loop = 0; loop < fehlt; loop++) {
					field.append(" ");
				}
				field.append(returnApeend());
				listeofcontains.add(key);
				i++;
				if (i == NUMBERFORONELINE) {
					i = 0;
					break;
				}
			}
		}
		return field.toString();
	}


}
