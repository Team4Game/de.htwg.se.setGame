package de.htwg.se.setgame.util.persistance.hibernate;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;

import de.htwg.se.setgame.util.persistance.hibernate.pojo.Card;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.CardsInField;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.Game;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.Player;

import java.util.*;

/**
 * Created by David on 04.01.16.
 */
public class GameDaoMapper {


    private static List<ICard> ICards;

    public GameDaoMapper(){


    }
    public static Game getGame(IGame game){
        Game result = new Game();
        result.setCardsInFieldTable(new CardsInField());
        result.setPlayerOne(game.getPlayerOne());
        result.setPlayerTwo(game.getPlayerTwo());
        result.setCardInField(game.getCardInField());
        result.setUnusedCards(game.getUnusedCards());
        result.setCardInField(game.getCardInField());
        result.setGameToken(game.getToken());

        return  result;

    }

    public static Set<Card> getUnusedCard(List<ICard> unusedCards) {
        Set<Card> result = new TreeSet<Card>();
        for(ICard card : unusedCards){
            result.add(getCard(card));
        }
        return result;
    }

    public static Player getPlayer(IPlayer player){
        Player result = new Player();
        result.setCounter(player.getCounter());
        result.setId(player.getId());
        return result;
    }
    public static Card getCard(ICard card){
        Card result = new Card();
        result.setColor(card.getColor());
        result.setForm(card.getForm());
        result.setNumberOfComponents(card.getNumberOfComponents());
        result.setPanelFilling(card.getPanelFilling());
        return result;
    }

    public static List<ICard> getICards(Set<Card> unusedCards) {
        List<ICard> result = new LinkedList<ICard>();
        for(Card card : unusedCards){
            result.add(card);
        }
        return result;
    }



    public static Map<Integer, Card> getCardsInField(Map<Integer, ICard> cardsInField) {
        Map<Integer, Card> result = new TreeMap<Integer, Card>();
        for(Integer key : cardsInField.keySet()){
            result.put(key, getCard(cardsInField.get(key)));
        }
        return result;
    }

    public static Map<Integer, ICard> getICardsInField(Map<Integer, Card> cardsInField) {
        Map<Integer, ICard> result = new TreeMap<Integer, ICard>();
        for(Integer key : cardsInField.keySet()){
            result.put(key, cardsInField.get(key));
        }
        return result;
    }

    public static IGame getIGame(Game game) {
        IGame result = new Game();
        result.setPlayerOne(game.getPlayerOne());
        result.setPlayerTwo(game.getPlayerTwo());
        result.setCardInField(game.getCardInField());
        result.setUnusedCards(game.getUnusedCards());
        result.setCardInField(game.getCardInField());
        result.setGameToken(game.getToken());
        return  result;
    }
}
