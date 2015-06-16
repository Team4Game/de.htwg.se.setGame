package de.htwg.se.setgame.util.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by raina on 15.06.2015.
 */
public final class SessionServiceHibernate {
    private static SessionFactory sessionFactory = null;
    private SessionServiceHibernate() {

    }

    public static Session getSession() {
        if (sessionFactory == null) {
            sessionFactory = new AnnotationConfiguration().configure()
                    .buildSessionFactory();

        }
        return sessionFactory.openSession();
    }

}
