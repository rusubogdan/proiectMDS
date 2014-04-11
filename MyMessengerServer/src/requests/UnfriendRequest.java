package requests;

public class UnfriendRequest extends Request {

	private static final long serialVersionUID = 1L;

	// din client catre server sterge in bd linia cu id-ul meu si cel al
	// prietenului ; nu cred ca am nevoie de nici un mesaj;
	protected UnfriendRequest(String myUsername, String myFriendUsername) {
		super(myUsername, myFriendUsername, null);
	}

}
