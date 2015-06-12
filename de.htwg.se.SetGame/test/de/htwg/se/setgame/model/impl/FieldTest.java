package de.htwg.se.setgame.model.impl;



import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.IModelFactory;
import org.junit.Before;
import org.junit.Test;


import java.util.LinkedList;
import java.util.List;


public class FieldTest {
	IField target;
    IModelFactory modelFactory;
	@Before
	public void setUp() {
        modelFactory = new ModelFactory();
		this.target = modelFactory.createField();
	}
    @Test
    public void setFieldCards(){
        List<ICard> list = new LinkedList<ICard>();
        
    }



}