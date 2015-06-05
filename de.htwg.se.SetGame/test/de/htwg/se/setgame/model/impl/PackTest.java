package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IPack;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;

public class PackTest {
	IPack target;

	@Before
	public void setUp() {
		this.target = new Pack();
	}

	@Test
	public void testCard() {
		Assert.assertTrue(this.target != null);
	}


}
