package de.htwg.se.setgame.controller.impl.logic.impl;


import de.htwg.se.setgame.model.ICard;
import de.htwg.se.setgame.model.IModelFactory;
import de.htwg.se.setgame.model.IPack;
import de.htwg.se.setgame.model.impl.ModelFactory;
import de.htwg.se.setgame.model.impl.Pack;
import de.htwg.se.setgame.model.impl.atributte.CardAtributen;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by raina on 03.06.2015.
 */
public class PackProviderTest {


    public static final String[] FORME = {"ovally", "wave", "balk"};
    private PackProvider target;

    @Test
    public void testGetCards() {
        this.target = new PackProvider(new ModelFactory());
        boolean b = false;

        for (ICard cards : target.getPack().getPack()) {
            if (cards.getColor().equals(CardAtributen.COLORS[0])
                    || cards.getColor().equals(CardAtributen.COLORS[1])
                    || cards.getColor().equals(CardAtributen.COLORS[2])
                    && cards.getForm().equals(CardAtributen.FORME[0])
                    || cards.getForm().equals(CardAtributen.FORME[1])
                    || cards.getForm().equals(CardAtributen.FORME[2])
                    && cards.getPanelFilling().equals(CardAtributen.FILL[0])
                    || cards.getPanelFilling().equals(CardAtributen.FILL[1])
                    || cards.getPanelFilling().equals(CardAtributen.FILL[2])
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







