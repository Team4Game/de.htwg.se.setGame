package de.htwg.se.setgame.model.impl;

import de.htwg.se.setgame.model.IPack;
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

		for (ICard cartas : IPack.getPack()) {
			if (cartas.getColor().equals(Pack.COLORS[0])
					|| cartas.getColor().equals(Pack.COLORS[1])
					|| cartas.getColor().equals(Pack.COLORS[2])
					&& cartas.getForm().equals(Pack.FORME[0])
					|| cartas.getForm().equals(Pack.FORME[1])
					|| cartas.getForm().equals(Pack.FORME[2])
					&& cartas.getPanelFilling().equals(Pack.FILL[0])
					|| cartas.getPanelFilling().equals(Pack.FILL[1])
					|| cartas.getPanelFilling().equals(Pack.FILL[2])
					&& cartas.getNumberOfComponents() == 1
					|| cartas.getNumberOfComponents() == 2
					|| cartas.getNumberOfComponents() == 3) {
				b = true;
			} else {
				b = false;
			}

		}
		assert (b != false);

	}
}
