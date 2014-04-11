package requests;

public class SignOutRequest extends Request {

	private static final long serialVersionUID = 1L;

	// il trimit din client cu user = this si astept un mesaj de tip logOut
	protected SignOutRequest(String username) {
		super(username, null, null);
	}

}
