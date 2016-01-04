package de.htwg.se.setgame.util.persistance.hibernate.pojo;


import de.htwg.se.setgame.model.IPlayer;

import javax.persistence.*;


/**
 * Created by David on 04.01.16.
 */
@Entity
@Table(name = "player")
public class Player implements IPlayer {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerID;
    @Column(nullable = false)
    private Integer pid;
    @Column(nullable = false)
    private Integer counter;



    @Override
    public void setId(int id) {
        this.pid = id;
    }

    @Override
    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int getId() {
        return this.pid;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }


    public Long getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }
}
