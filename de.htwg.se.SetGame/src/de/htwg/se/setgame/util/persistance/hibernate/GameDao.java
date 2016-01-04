package de.htwg.se.setgame.util.persistance.hibernate;

import de.htwg.se.setgame.model.IGame;
import de.htwg.se.setgame.util.persistance.IGameDao;
import de.htwg.se.setgame.util.persistance.hibernate.pojo.Game;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;


/**
 * Created by David on 04.01.16.
 */
public class GameDao implements IGameDao {

    private static Session session;
    private static SessionFactory sessionFactory;

    private static Session getSession(){
        if(sessionFactory == null) {
            sessionFactory = new AnnotationConfiguration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory.openSession();
    }

    @Override
    public boolean createOrUpdate(IGame gameObj) {
        if(findGame(gameObj.getToken()) != null){
            deleteGame(gameObj);
        } else {
            Game game = GameDaoMapper.getGame(gameObj);
            saveGame(game);
        }
        if(findGame(gameObj.getToken()) != null) {
            return true;
        }
        return false;
    }

    private void saveGame(Game game) {
        Transaction transaction = null;
        Session session = getSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(game);
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        session.flush();
        transaction.commit();
        session.close();
    }

    //WICHTIG!
    private void deleteGame(IGame game){
        Session session = getSession();
        Game result = (Game) session.createCriteria(Game.class).add(Restrictions.eq("token", game.getToken())).uniqueResult();
        session.close();

        if(result != null){
            Session sessionDel = getSession();
            Transaction transaction = null;

            transaction = sessionDel.beginTransaction();
            sessionDel.delete(result);

            sessionDel.flush();
            transaction.commit();
            sessionDel.close();
        }

    }

    @Override
    public IGame findGame(String id) {
        IGame result = null;
        Session session = getSession();
        Game game = (Game) session.createCriteria(Game.class).add(Restrictions.eq("token", id)).uniqueResult();

        session.flush();
        session.close();
        return result;
    }

    @Override
    public void closeDb() {
        getSession().close();
    }
}
