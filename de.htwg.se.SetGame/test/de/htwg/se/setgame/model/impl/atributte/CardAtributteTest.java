package de.htwg.se.setgame.model.impl.atributte;

import de.htwg.se.setgame.util.persistence.couchdb.CouchDBSession;
import javassist.Modifier;
import org.ektorp.CouchDbConnector;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Created by raina on 17.06.2015.
 */
public class CardAtributteTest {
    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = CardAtributen.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
    @Test
    public void getDaoManagerSession_ok(){
        String[] FORME = CardAtributen.FORME;
        String[] COLORS = CardAtributen.COLORS;
       String[] FILL = CardAtributen.FILL;
        Assert.assertTrue(FORME != null);
        Assert.assertTrue(COLORS != null);
        Assert.assertTrue(FILL != null);
    }
}
