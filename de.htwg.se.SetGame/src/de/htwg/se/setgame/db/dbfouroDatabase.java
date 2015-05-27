package de.htwg.se.setgame.db;


import com.sun.org.apache.xml.internal.security.signature.ObjectContainer;

/**
 * Created by David on 20.05.15.
 */
public class dbfouroDatabase {

    // accessDb4o
    ////

    ObjectContainer db =  Db4oEmbedded.openFile(Db4oEmbedded
            .newConfiguration(), DB4OFILENAME);
    try {
        // do something with db4o
    } finally {
        db.close();
    }

}
