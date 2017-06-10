package de.htwg.se.setgame.util.persistence.db4o;

import com.db4o.ObjectContainer;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * Created by raina on 16.06.2015.
 */
public class Db4oSessionManagerTest {
    @Test
    public void testConstructorIsPrivate() throws Exception {
        Constructor constructor = Db4oSessionManager.class.getDeclaredConstructor();
        Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }
    @Test
    public void getDaoManagerSession_ok(){
        ObjectContainer db = Db4oSessionManager.getDbObjectContainer();
        Assert.assertTrue(db != null);
    }
}
