package miwalz.testing;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.ektorp.support.CouchDbDocument;

@SuppressWarnings("serial")
@Entity
@Table(name = "setgame")
public class Model extends CouchDbDocument {
	
	@Id  
	private int uid;
	
	private String foo;

	public String getFoo() {
		return this.foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
}
