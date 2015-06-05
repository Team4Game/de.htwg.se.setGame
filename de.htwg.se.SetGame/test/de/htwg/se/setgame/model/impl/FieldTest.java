package de.htwg.se.setgame.model.impl;



import de.htwg.se.setgame.model.IField;
import de.htwg.se.setgame.model.IModelFactory;
import org.junit.Before;
import org.junit.Test;


public class FieldTest {
	IField target;

	@Before
	public void setUp() {
        IModelFactory modelFactory = new ModelFactory();
		this.target = modelFactory.createField();
	}
    @Test
    public void setFieldCards(){

    }



}