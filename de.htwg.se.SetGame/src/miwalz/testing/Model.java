package miwalz.testing;

import org.ektorp.support.CouchDbDocument;

public class Model extends CouchDbDocument {

	private static final long serialVersionUID = 1L;
	private String foo;

	public String getFoo() {
		return this.foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}
	
}
