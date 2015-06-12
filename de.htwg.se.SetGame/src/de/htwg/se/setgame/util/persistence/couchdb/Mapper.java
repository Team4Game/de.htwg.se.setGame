package de.htwg.se.setgame.util.persistence.couchdb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.model.impl.Card;
import de.htwg.se.setgame.model.impl.Game;
import de.htwg.se.setgame.model.impl.Player;

public class Mapper {

	@SuppressWarnings("unchecked")
	public PersistentGame getPersistentGame(IGame game) {
		String id = game.getId();
		PersistentPlayer playerOne = new PersistentPlayer(game.getPlayerOne().getPid(), game.getPlayerOne().getCounter());
		PersistentPlayer playerTwo = new PersistentPlayer(game.getPlayerTwo().getPid(), game.getPlayerTwo().getCounter());
		int counter = game.getCounter();
		Collection<PersistentCard> cardsInField = new ArrayList<PersistentCard>(); // get via Field.getCardsInField
		Collection<PersistentCard> unusedCards = new ArrayList<PersistentCard>(); // get via Pack.getPack
		
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

	@SuppressWarnings("unchecked")
	public IGame getGame(PersistentGame persistentGame) {
		String id = persistentGame.getId();
		IPlayer playerOne = new Player(persistentGame.getPlayerOne().getPid(), persistentGame.getPlayerOne().getCounter());
		IPlayer playerTwo = new Player(persistentGame.getPlayerTwo().getPid(), persistentGame.getPlayerTwo().getCounter());
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
		
		IGame game = new Game(id, playerOne, playerTwo, counter, cardsInField, unusedCards);
		
		return game;
	}
}