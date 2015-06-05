package miwalz.testing;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.hibernate.*;
import org.hibernate.cfg.*;

public class Controller {

	public void testCouch() {

		Model model = new Model();
		model.setFoo("zwei");

		
		// COUCH DB

		
		HttpClient httpClient = new StdHttpClient.Builder()
				.host("lenny2.in.htwg-konstanz.de").port(5984).build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector db = new StdCouchDbConnector("setgame", dbInstance);
		db.createDatabaseIfNotExists();

		db.create(model);

		// both id and revision will be set after create
		System.out.println(model.getId());
		System.out.println(model.getRevision());
		System.out.println("couchdb ok");
		
		
		// HIBERNATE

		Session session = new AnnotationConfiguration().configure()
				.buildSessionFactory().openSession();
		
		Transaction transaction = null;
		
		// save
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(model);
			transaction.commit();
			System.out.println("hibernate ok");
			System.out.println(model.getId());
		} catch (HibernateException ex) {
			System.out.println("hibernate error");
			if (transaction != null) {
				System.out.println("hibernate rollback");
				transaction.rollback();
			}
		}
		
		/*
		// fetch
		try {
			transaction = session.beginTransaction();
			session.get(Model.class, arg1)
			transaction.commit();
			System.out.println("hibernate ok");
		} catch (HibernateException ex) {
			System.out.println("hibernate error");
			if (transaction != null) {
				System.out.println("hibernate rollback");
				transaction.rollback();
			}
		}
		*/
	}

}
