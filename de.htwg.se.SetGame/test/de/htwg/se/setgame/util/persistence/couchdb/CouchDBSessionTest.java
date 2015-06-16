package de.htwg.se.setgame.util.persistence.couchdb;

import de.htwg.se.setgame.util.persistence.hibernate.SessionServiceHibernate;
import javassist.Modifier;
import org.ektorp.CouchDbConnector;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Created by raina on 16.06.2015.
 */
public class CouchDBSessionTest {
    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = CouchDBSession.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
    @Test
    public void getDaoManagerSession_ok(){
        CouchDbConnector conector = CouchDBSession.getCouchDbConnector();
        Assert.assertTrue(conector != null);
    }
}
