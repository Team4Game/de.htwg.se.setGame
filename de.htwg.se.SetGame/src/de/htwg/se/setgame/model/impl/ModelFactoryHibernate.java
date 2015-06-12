package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IModelFactoryPersistent;
import de.htwg.se.setgame.util.persistence.hibernate.Mapper;
import de.htwg.se.setgame.util.persistence.hibernate.PersistentCard;
import de.htwg.se.setgame.util.persistence.hibernate.PersistentGame;
import de.htwg.se.setgame.util.persistence.hibernate.PersistentPlayer;

/**
 * Created by raina on 12.06.2015.
 */
public class ModelFactoryHibernate implements IModelFactoryPersistent {
    public PersistentPlayer getPersistentPlayer(){
        return new PersistentPlayer();
    }
    public PersistentGame getPersistentGame(){
        return new PersistentGame();
    }
    public PersistentCard getPersistentCard(){
        return new PersistentCard();

    }
    public Mapper getMapper(){
        return new Mapper();
    }

}
