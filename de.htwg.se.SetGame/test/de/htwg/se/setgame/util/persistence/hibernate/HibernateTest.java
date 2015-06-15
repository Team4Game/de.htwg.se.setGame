package de.htwg.se.setgame.util.persistence.hibernate;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.se.setgame.SetGameModule;
import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.util.persistence.IGameDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HibernateTest {
    IGameDao target;
    IModelFactory modelFactory;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new SetGameModule());
        this.modelFactory = injector.getInstance(IModelFactory.class);

    }

    @Test
    public void saveGameTest() {
        IPlayer playerOne = modelFactory.createPlayer();
        playerOne.setCounter(4);
        playerOne.setPid(0);
        IPlayer playerTwo = modelFactory.createPlayer();
        playerTwo.setCounter(3);
        playerTwo.setPid(2);
        int counter = 4;
        Map<Integer, ICard> cardsInField = new HashMap<Integer, ICard>();
        ICard cardfield = modelFactory.createCard();
        cardfield.setColor("red");
        cardfield.setNumberOfComponents(1);
        cardfield.setForm("wave");
        cardfield.setPanelFilling("fill");
        cardsInField.put(0, cardfield);
        ICard card = modelFactory.createCard();
        card.setColor("green");
        card.setNumberOfComponents(1);
        card.setForm("wave");
        card.setPanelFilling("fill");
        List<ICard> unusedCards = new LinkedList<ICard>();
        unusedCards.add(card);

        // generate unique id
        String uid = "b3192b4a-55ba-4adc-9047-764778fd89c9";

        IGame game = modelFactory.createGame();
        game.setId(uid);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        game.setCounter(counter);
        game.setCardsInField(cardsInField);
        game.setUnusedCards(unusedCards);
        IGameDao dao = new GameDao();
        dao.createOrUpdateGame(game);

        System.out.println(game.getId());

        dao.closeDb();
        Assert.assertEquals(uid, game.getId());

    }

    @Test
    public void getGame() {
        IGameDao gameDao = new GameDao();
        IGame game = gameDao.findGame("b3192b4a-55ba-4adc-9047-764778fd89c9");
        Assert.assertTrue(game != null);

    }

    @Test
    public void updateGame() {
        IPlayer playerOne = modelFactory.createPlayer();
        playerOne.setCounter(10);
        playerOne.setPid(1);
        IPlayer playerTwo = modelFactory.createPlayer();
        playerTwo.setCounter(11);
        playerTwo.setPid(2);
        int counter = 4;
        Map<Integer, ICard> cardsInField = new HashMap<Integer, ICard>();
        ICard cardfield = modelFactory.createCard();
        cardfield.setColor("red");
        cardfield.setNumberOfComponents(1);
        cardfield.setForm("wave");
        cardfield.setPanelFilling("halffill");
        cardsInField.put(0, cardfield);
        ICard card = modelFactory.createCard();
        card.setColor("green");
        card.setNumberOfComponents(1);
        card.setForm("wave");
        card.setPanelFilling("empty");
        List<ICard> unusedCards = new LinkedList<ICard>();
        unusedCards.add(card);

        // generate unique id
        String uid = "b3192b4a-55ba-4adc-9047-764778fd89c9";

        IGame game = modelFactory.createGame();
        game.setId(uid);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        game.setCounter(counter);
        game.setCardsInField(cardsInField);
        game.setUnusedCards(unusedCards);
        IGameDao dao = new GameDao();
        dao.createOrUpdateGame(game);
        Assert.assertTrue(10 == game.getPlayerOne().getCounter());
        Assert.assertTrue(11 == game.getPlayerTwo().getCounter());
        Assert.assertTrue(card.compareTo(game.getUnusedCards().get(0)));

    }
    @Test
    public void createAnotherGame(){
        IPlayer playerOne = modelFactory.createPlayer();
        playerOne.setCounter(10);
        playerOne.setPid(1);
        IPlayer playerTwo = modelFactory.createPlayer();
        playerTwo.setCounter(11);
        playerTwo.setPid(2);
        int counter = 4;
        Map<Integer, ICard> cardsInField = new HashMap<Integer, ICard>();
        ICard cardfield = modelFactory.createCard();
        cardfield.setColor("red");
        cardfield.setNumberOfComponents(1);
        cardfield.setForm("wave");
        cardfield.setPanelFilling("halffill");
        cardsInField.put(0, cardfield);
        ICard card = modelFactory.createCard();
        card.setColor("green");
        card.setNumberOfComponents(1);
        card.setForm("wave");
        card.setPanelFilling("empty");
        List<ICard> unusedCards = new LinkedList<ICard>();
        unusedCards.add(card);

        // generate unique id
        String uid = "b3192b4a-55ba-4adc-9047-764778fd89sddede";

        IGame game = modelFactory.createGame();
        game.setId(uid);
        game.setPlayerOne(playerOne);
        game.setPlayerTwo(playerTwo);
        game.setCounter(counter);
        game.setCardsInField(cardsInField);
        game.setUnusedCards(unusedCards);
        IGameDao dao = new GameDao();
        dao.createOrUpdateGame(game);
        Assert.assertTrue(10 == game.getPlayerOne().getCounter());
        Assert.assertTrue(11 == game.getPlayerTwo().getCounter());
        Assert.assertTrue(card.compareTo(game.getUnusedCards().get(0)));

    }

}
