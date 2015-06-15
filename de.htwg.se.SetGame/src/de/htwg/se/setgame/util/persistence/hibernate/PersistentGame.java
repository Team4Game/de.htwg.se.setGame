package de.htwg.se.setgame.util.persistence.hibernate;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.IndexColumn;

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
    @NotNull
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
















/**
 * Created by miwalz on 03.06.2015.
 

@Entity
@Table(name = "setgame")
public class PersistentGame implements IGame {

	@Id  
	private int id;
	
	private IPlayer player1;
	private IPlayer player2;

	@OneToMany(mappedBy="persistentGame")
	private List<ICard> cardsInField; // get via Field.getCardsInField
	
	@OneToMany(mappedBy="persistentGame")
	private List<ICard> unusedCards; // get via Pack.getPack

	private String token;

	public PersistentGame(IPlayer player1, IPlayer player2, List<ICard> cardsInField,
			List<ICard> unusedCards, String token) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.cardsInField = cardsInField;
		this.unusedCards = unusedCards;
		this.token = token;
	}

	@Override
	public void setUnusedCards(List<ICard> unusedCards) {
		this.unusedCards = unusedCards;
	}

	@Override
	public IPlayer getPlayerOne() {
		return this.player1;
	}

	@Override
	public IPlayer getPlayerTwo() {
		return this.player2;
	}

	@Override
	public List<ICard> getUnusedCards() {
		return this.unusedCards;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void setPlayerOne(IPlayer player1) {
		this.player1 = player1;
	}

	@Override
	public void setPlayerTwo(IPlayer player2) {
		this.player2 = player2;
	}

	@Override
	public void setCardsInField(Map<Integer, ICard> cardInField) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCounter(int counter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, ICard> getCardsInField() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCounter() {
		// TODO Auto-generated method stub
		return 0;
	}
}
*/
