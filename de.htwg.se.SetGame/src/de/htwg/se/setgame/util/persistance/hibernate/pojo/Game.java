package de.htwg.se.setgame.util.persistance.hibernate.pojo;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.model.IPlayer;
import de.htwg.se.setgame.util.persistance.hibernate.GameDaoMapper;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by David on 04.01.16.
 */

@Entity
@Table(name = "setgamegame")
public class Game implements IGame {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gameID;



    @Column(name = "cardsforfield")
    @ElementCollection
    private Map<Integer, Card> cardsInField;

    @Column(name = "token")
    private String token;


    @OneToOne(optional = false)
    private Player playerOne;
    @OneToOne(optional = false)
    private Player playerTwo;
    @Column
    @OneToMany(targetEntity = Card.class)
    private Set<Card> unusedCards;

    @Override
    public void setPlayerOne(IPlayer player1) {
        this.playerOne = GameDaoMapper.getPlayer(player1);
    }

    @Override
    public void setPlayerTwo(IPlayer player2) {
        this.playerTwo = GameDaoMapper.getPlayer(player2);
    }

    @Override
    public void setCardInField(Map<Integer, ICard> cardsInField) {

        this.cardsInField = GameDaoMapper.getCardsInField(cardsInField);
    }

    @Override
    public void setUnusedCards(List<ICard> unusedCards) {
        this.unusedCards = GameDaoMapper.getUnusedCard(unusedCards);
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
        return GameDaoMapper.getICardsInField(cardsInField);
    }

    @Override
    public List<ICard> getUnusedCards() {
        return GameDaoMapper.getICards(unusedCards);
    }

    @Override
    public String getToken() {
        return token;
    }
}
