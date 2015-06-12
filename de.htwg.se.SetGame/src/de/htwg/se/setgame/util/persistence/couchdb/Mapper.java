package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.model.*;
import de.htwg.se.setgame.model.impl.Card;
import de.htwg.se.setgame.model.impl.Game;
import de.htwg.se.setgame.model.impl.Player;

import java.util.*;

public class Mapper {

    @SuppressWarnings("unchecked")
    ModelFactoryCouchDb modelFactoryCouchDb;
    IModelFactory modelFactory;

    public PersistentGame getPersistentGame(IGame game, IModelFactoryPersistent modelFactoryCouchDb, IModelFactory modelFactory) {
        this.modelFactoryCouchDb =(ModelFactoryCouchDb) modelFactoryCouchDb;
        this.modelFactory = modelFactory;
        String id = game.getId();
		PersistentPlayer playerOne = new PersistentPlayer(game.getPlayerOne().getPid(), game.getPlayerOne().getCounter());
		PersistentPlayer playerTwo = new PersistentPlayer(game.getPlayerTwo().getPid(), game.getPlayerTwo().getCounter());
		int counter = game.getCounter();
		Collection<PersistentCard> cardsInField = new ArrayList<PersistentCard>(); // get via Field.getCardsInField
		Collection<PersistentCard> unusedCards = new ArrayList<PersistentCard>(); // get via Pack.getPack
		
		for (Map.Entry<Integer, ICard> entry : game.getCardsInField().entrySet()) {
			ICard card = entry.getValue();
            PersistentCard persistentCard = ((ModelFactoryCouchDb) modelFactoryCouchDb).getPersistentCard();
            persistentCard.setColor(card.getColor());
            persistentCard.setForm(card.getForm());
            persistentCard.setPanelFilling(card.getPanelFilling());
            persistentCard.setNumberOfComponents(card.getNumberOfComponents());
			cardsInField.add(persistentCard);
		}
		for (ICard card : game.getUnusedCards()) {
            PersistentCard persistentCard = ((ModelFactoryCouchDb) modelFactoryCouchDb).getPersistentCard();
            persistentCard.setColor(card.getColor());
            persistentCard.setForm(card.getForm());
            persistentCard.setPanelFilling(card.getPanelFilling());
            persistentCard.setNumberOfComponents(card.getNumberOfComponents());
            cardsInField.add(persistentCard);
            unusedCards.add(persistentCard);
		}
		
		PersistentGame persGame = ((ModelFactoryCouchDb) modelFactoryCouchDb).getPersistentGame();
        persGame.setId(id);
        persGame.setPlayerOne(playerOne);
        persGame.setPlayerTwo(playerTwo);
        persGame.setCardsInField(cardsInField);
        persGame.setUnusedCards(unusedCards);
        persGame.setCounter(counter);

		return persGame;
	}

	@SuppressWarnings("unchecked")
	public IGame getGame(PersistentGame persistentGame) {
		String id = persistentGame.getId();
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
            cardsInField.put(key, card);
			key++;
		}
		for (PersistentCard persCard : persistentGame.getUnusedCards()) {
            ICard card = modelFactory.createCard();
            card.setColor(persCard.getColor());
            card.setForm(persCard.getForm());
            card.setPanelFilling(persCard.getPanelFilling());
            card.setNumberOfComponents(persCard.getNumberOfComponents());
			unusedCards.add(card);
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