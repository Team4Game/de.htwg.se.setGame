package miwalz.testing;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

public class Controller {

	public void testCouch() {

		HttpClient httpClient = new StdHttpClient.Builder()
				.host("lenny2.in.htwg-konstanz.de")
				.port(5984)
				.build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		CouchDbConnector db = new StdCouchDbConnector("setgame", dbInstance);
		db.createDatabaseIfNotExists();
		
		Model model = new Model();
		model.setFoo("zwei");

		db.create(model);
		
		// both id and revision will be set after create
		System.out.println(model.getId());
		System.out.println(model.getRevision());
		
		System.out.println("testCouch ok");
		
		return;

	}

}
