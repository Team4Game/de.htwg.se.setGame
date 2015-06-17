package de.htwg.se.setgame.util.persistence.hibernate;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPlayer;

import java.util.*;

public class Mapper {
    private IModelFactory modelFactory;

    public Mapper(IModelFactory modelFactory){
        this.modelFactory = modelFactory;
    }




    public PersistentGame getPersistentGame(IGame game) {
        String id = game.getId();
        PersistentPlayer playerOne = new PersistentPlayer();
        playerOne.setPid(game.getPlayerOne().getPid());
        playerOne.setCounter(game.getPlayerOne().getCounter());
        PersistentPlayer playerTwo = new PersistentPlayer();
        playerTwo.setPid(game.getPlayerTwo().getPid());
        playerTwo.setCounter(game.getPlayerTwo().getCounter());
        int counter = game.getCounter();
		Collection<PersistentCard> cardsInField = new ArrayList<PersistentCard>();
		Collection<PersistentCard> unusedCards = new ArrayList<PersistentCard>();

        for (Map.Entry<Integer, ICard> entry : game.getCardsInField().entrySet()) {
			ICard card = entry.getValue();
            PersistentCard persistentCard = new PersistentCard();
            persistentCard.setColor(card.getColor());
            persistentCard.setForm(card.getForm());
            persistentCard.setPanelFilling(card.getPanelFilling());
            persistentCard.setNumberOfComponents(card.getNumberOfComponents());
            cardsInField.add(persistentCard);
        }
		for (ICard card : game.getUnusedCards()) {
            PersistentCard persistentCard = new PersistentCard();
            persistentCard.setColor(card.getColor());
            persistentCard.setForm(card.getForm());
            persistentCard.setPanelFilling(card.getPanelFilling());
            persistentCard.setNumberOfComponents(card.getNumberOfComponents());

            unusedCards.add(persistentCard);
        }

        PersistentGame persGame = new PersistentGame();
        persGame.setGameID(id);
        persGame.setPlayerOne(playerOne);
        persGame.setPlayerTwo(playerTwo);
        persGame.setCardsInField(cardsInField);
        persGame.setUnusedCards(unusedCards);
        persGame.setCounter(counter);

        return persGame;
    }

	public IGame getGame(PersistentGame persistentGame) {
        String id = persistentGame.getGameID();
        IPlayer playerOne = modelFactory.createPlayer();
        playerOne.setPid(persistentGame.getPlayerOne().getPid());
        playerOne.setCounter(persistentGame.getPlayerOne().getCounter());
        IPlayer playerTwo = modelFactory.createPlayer();
        playerTwo.setPid(persistentGame.getPlayerTwo().getPid());
        playerTwo.setCounter(persistentGame.getPlayerTwo().getCounter());
        int counter = persistentGame.getCounter();
		Map<Integer, ICard> cardsInField = new HashMap<Integer, ICard>();
		List<ICard> unusedCards = new ArrayList<ICard>();
		
		int key = 0;
		for (PersistentCard persCard : persistentGame.getCardsInField()) {
			ICard card = modelFactory.createCard();
            card.setColor(persCard.getColor());
            card.setForm(persCard.getForm());
            card.setPanelFilling(persCard.getPanelFilling());
            card.setNumberOfComponents(persCard.getNumberOfComponents());
            cardsInField.put(key,card);
			key++;
		}
		for (PersistentCard persCard : persistentGame.getUnusedCards()) {
            ICard cardForUnusedList = modelFactory.createCard();
            cardForUnusedList.setColor(persCard.getColor());
            cardForUnusedList.setForm(persCard.getForm());
            cardForUnusedList.setPanelFilling(persCard.getPanelFilling());
            cardForUnusedList.setNumberOfComponents(persCard.getNumberOfComponents());
			unusedCards.add(cardForUnusedList);
		}

        IGame game = modelFactory.createGame();
        game.setId(id);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        game.setCounter(counter);
        game.setCardsInField(cardsInField);
        game.setUnusedCards(unusedCards);


        return game;
	}
}