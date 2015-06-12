package de.htwg.se.setgame.util.persistence.hibernate;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.model.impl.Card;
import de.htwg.se.setgame.model.impl.ModelFactoryHibernate;
import de.htwg.se.setgame.util.IMapper;

import java.util.*;

public class Mapper implements IMapper {
    IModelFactory modelFactory;

    public PersistentGame getPersistentGame(IGame game, ModelFactoryHibernate modelFactoryPersistent, IModelFactory modelFactory) {
        String id = game.getId();
        PersistentPlayer playerOne = modelFactoryPersistent.getPersistentPlayer();
        playerOne.setPid(game.getPlayerOne().getPid());
        playerOne.setCounter(game.getPlayerOne().getCounter());
        PersistentPlayer playerTwo = modelFactoryPersistent.getPersistentPlayer();
        playerTwo.setPid(game.getPlayerTwo().getPid());
        playerTwo.setCounter(game.getPlayerTwo().getCounter());
        int counter = game.getCounter();
		Collection<PersistentCard> cardsInField = new ArrayList<PersistentCard>(); // get via Field.getCardsInField
		Collection<PersistentCard> unusedCards = new ArrayList<PersistentCard>(); // get via Pack.getPack
        this.modelFactory = modelFactory;
        for (Map.Entry<Integer, ICard> entry : game.getCardsInField().entrySet()) {
			ICard card = entry.getValue();
			cardsInField.add(new PersistentCard(card.getColor(), card.getForm(), card.getPanelFilling(), card.getNumberOfComponents()));
		}
		for (ICard card : game.getUnusedCards()) {
			unusedCards.add(new PersistentCard(card.getColor(), card.getForm(), card.getPanelFilling(), card.getNumberOfComponents()));
		}
		
		PersistentGame persGame = new PersistentGame(id, playerOne, playerTwo, cardsInField, unusedCards, counter);
		
		return persGame;
	}

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
			cardsInField.put(key, new Card(persCard.getColor(), persCard.getForm(), persCard.getPanelFilling(), persCard.getNumberOfComponents()));
			key++;
		}
		for (PersistentCard persCard : persistentGame.getUnusedCards()) {
			unusedCards.add(new Card(persCard.getColor(), persCard.getForm(), persCard.getPanelFilling(), persCard.getNumberOfComponents()));
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