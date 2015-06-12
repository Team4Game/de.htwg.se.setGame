package de.htwg.se.setgame.model;


import de.htwg.se.setgame.util.persistence.couchdb.Mapper;
import de.htwg.se.setgame.util.persistence.couchdb.PersistentCard;
import de.htwg.se.setgame.util.persistence.couchdb.PersistentGame;
import de.htwg.se.setgame.util.persistence.couchdb.PersistentPlayer;

/**
 * Created by raina on 12.06.2015.
 */
public class ModelFactoryCouchDb implements IModelFactoryPersistent {
    public PersistentPlayer getPersistentPlayer(){
        return new PersistentPlayer();
    }
    public PersistentGame getPersistentGame(){
        return new PersistentGame();
    }
    public PersistentCard getPersistentCard(){
        return new PersistentCard();

    }
    public  Mapper getMapper(){
        return new Mapper();
    }

}
