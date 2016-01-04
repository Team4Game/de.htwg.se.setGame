package de.htwg.se.setgame.util.persistance.hibernate.pojo;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by David on 04.01.16.
 */

@Entity
@Table(name = "setgame_game")
public class Game implements IGame {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gameID;



    @Column(name = "cards_in_field")
    @MapKey(name = "number")
    private Map<Integer, ICard> cardsInField = new TreeMap<Integer, ICard>();

    @Column(name = "token")
    private String token;


    @OneToOne(optional = false)
    private Player playerOne;
    @OneToOne(optional = false)
    private Player playerTwo;
    @Column(nullable = false)
    private int counter;
    @Column
    @OneToMany(targetEntity = Card.class)
    private List<ICard> unusedCards;

    @Override
    public void setPlayerOne(IPlayer player1) {
        this.playerOne = (Player) player1;
    }

    @Override
    public void setPlayerTwo(IPlayer player2) {
        this.playerTwo = (Player) player2;
    }

    @Override
    public void setCardInField(Map<Integer, ICard> cardsInField) {
        this.cardsInField = cardsInField;
    }

    @Override
    public void setUnusedCards(List<ICard> unusedCards) {
        this.unusedCards = unusedCards;
    }

    @Override
    public void setGameToken(String token) {
        this.token = token;
    }

    @Override
    public IPlayer getPlayerOne() {
        return playerOne;
    }

    @Override
    public IPlayer getPlayerTwo() {
        return playerTwo;
    }

    @Override
    public Map<Integer, ICard> getCardInField() {
        return cardsInField;
    }

    @Override
    public List<ICard> getUnusedCards() {
        return unusedCards;
    }

    @Override
    public String getToken() {
        return token;
    }
}
