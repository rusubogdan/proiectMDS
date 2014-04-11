package requests;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;
	protected String passwordAgain;

	@SuppressWarnings("unused")
	private Request() {
		// sa impiedic un request fara parametrii
	}

	protected Request(String username, String password, String passwordAgain ) {
		this.username = username;
		this.password = password;
		this.passwordAgain = passwordAgain;
	}
	
	
	
}
