package requests;

import java.io.Serializable;

public class SignInRequest extends Request implements Serializable {
	private static final long serialVersionUID = 1L;

	// trimit un request prin socket si primesc un mesaj fie de tip succes fie
	// de tip insucces; in caz de succes pe langa mesaj primesc si informatii
	// auxiliare cum ar fi lista de prieteni activi;

	public SignInRequest(String username, String password, String passwordAgain) {
		super(username, password, null);
	}


}
