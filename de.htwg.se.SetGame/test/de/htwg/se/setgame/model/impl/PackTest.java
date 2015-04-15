package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IPack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.impl.Pack;

public class PackTest {
	IPack IPack;

	@Before
	public void setUp() {
		this.IPack = new Pack();
	}

	@Test
	public void testCard() {
		assert (this.IPack != null);
	}

	@Test
	public void testGetCards() {
		this.IPack = new Pack();
		boolean b = false;

		for (ICard cards : IPack.getPack()) {
			if (cards.getColor().equals(Pack.COLORS[0])
					|| cards.getColor().equals(Pack.COLORS[1])
					|| cards.getColor().equals(Pack.COLORS[2])
					&& cards.getForm().equals(Pack.FORME[0])
					|| cards.getForm().equals(Pack.FORME[1])
					|| cards.getForm().equals(Pack.FORME[2])
					&& cards.getPanelFilling().equals(Pack.FILL[0])
					|| cards.getPanelFilling().equals(Pack.FILL[1])
					|| cards.getPanelFilling().equals(Pack.FILL[2])
					&& cards.getNumberOfComponents() == 1
					|| cards.getNumberOfComponents() == 2
					|| cards.getNumberOfComponents() == 3) {
				b = true;
			} else {
				b = false;
			}

		}
        Assert.assertTrue(b != false);

	}
}
