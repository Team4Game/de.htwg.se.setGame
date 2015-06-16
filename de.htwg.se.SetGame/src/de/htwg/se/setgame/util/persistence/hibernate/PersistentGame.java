package de.htwg.se.setgame.util.persistence.hibernate;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.TreeSet;

@Entity
@Table(name = "setgame_game")
public class PersistentGame implements Serializable {

	private static final long serialVersionUID = -2065551404391826048L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameID;

    @OneToOne
    private PersistentPlayer playerOne;
    @OneToOne
    private PersistentPlayer playerTwo;
    private int counter;


    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="SETGAME_UNUSED_CARDS", joinColumns={@JoinColumn(name="GAME_ID", referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(name="UNUSEDCARD_ID", referencedColumnName="cardID",nullable=true)})
    private Collection<PersistentCard> unusedCards = new TreeSet<PersistentCard>(); // get via Pack.getPack

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="SETGAME_CARDS_IN_FIELD", joinColumns={@JoinColumn(name="GAME_ID", referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(name="CARD_IN_FIELD_ID", referencedColumnName="cardID",nullable=true)})
    private Collection<PersistentCard> cardsInField = new TreeSet<PersistentCard>();
    // get via Field.getCardsInField


    public PersistentGame() {
    }


    public String getGameID() {
        return gameID;
    }

    public void setGameID(String id) {
        this.gameID = id;
    }

    public PersistentPlayer getPlayerOne() {
		return playerOne;
	}

	public void setPlayerOne(PersistentPlayer playerOne) {
		this.playerOne = playerOne;
	}

	public PersistentPlayer getPlayerTwo() {
		return playerTwo;
	}

	public void setPlayerTwo(PersistentPlayer playerTwo) {
		this.playerTwo = playerTwo;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

    public Collection<PersistentCard> getCardsInField() {
        return cardsInField;
    }

    public void setCardsInField(Collection<PersistentCard> cardsInField) {
        this.cardsInField = cardsInField;
    }
    public Collection<PersistentCard> getUnusedCards() {
        return unusedCards;
	}

	public void setUnusedCards(Collection<PersistentCard> unusedCards) {
		this.unusedCards = unusedCards;
	}

    public Long getId() {
        return id;
    }


}

















