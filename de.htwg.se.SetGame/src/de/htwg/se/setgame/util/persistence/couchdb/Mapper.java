package de.htwg.se.setgame.util.persistence.couchdb;

import com.google.inject.Inject;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPlayer;

import java.util.*;

public class Mapper {


    private IModelFactory modelFactory;

    @Inject
    public Mapper(IModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }


    public IGame getPersistentGame(IGame game) {
        IGame persistentGame = new PersistentGame();
        //playerOne persistent
        IPlayer persistentPlayerOne = new PersistentPlayer();
        persistentPlayerOne.setPid(game.getPlayerOne().getPid());
        persistentPlayerOne.setCounter(game.getPlayerOne().getCounter());

        //playerTwo persistent
        IPlayer persistentPlayerTwo = new PersistentPlayer();
        persistentPlayerTwo.setPid(game.getPlayerTwo().getPid());
        persistentPlayerTwo.setCounter(game.getPlayerTwo().getCounter());

        List<ICard> unusedCards = new LinkedList<ICard>();
        for (ICard car : game.getUnusedCards()) {
            PersistentCard persistentCard = new PersistentCard();
            persistentCard.setColor(car.getColor());
            persistentCard.setForm(car.getForm());
            persistentCard.setPanelFilling(car.getPanelFilling());
            persistentCard.setNumberOfComponents(car.getNumberOfComponents());
            unusedCards.add(persistentCard);
        }
        Map<Integer, ICard> cardsInField = new HashMap<Integer, ICard>();
        for (Integer key : game.getCardsInField().keySet()) {
            ICard car = game.getCardsInField().get(key);
            PersistentCard persistentCard = new PersistentCard();
            persistentCard.setColor(car.getColor());
            persistentCard.setForm(car.getForm());
            persistentCard.setPanelFilling(car.getPanelFilling());
            persistentCard.setNumberOfComponents(car.getNumberOfComponents());
            cardsInField.put(key, persistentCard);
        }
        persistentGame.setId(game.getId());
        persistentGame.setCounter(game.getCounter());
        persistentGame.setPlayerOne(persistentPlayerOne);
        persistentGame.setPlayerTwo(persistentPlayerTwo);
        persistentGame.setUnusedCards(unusedCards);
        persistentGame.setCardsInField(cardsInField);
        return persistentGame;
    }


}