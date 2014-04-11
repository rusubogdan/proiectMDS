package requests;

import java.io.Serializable;

public class SignUpRequest extends Request implements Serializable {
	private static final long serialVersionUID = 1L;

	protected SignUpRequest(String username, String password, String passwordAgain) {
		super(username, password, passwordAgain);
	}


}
