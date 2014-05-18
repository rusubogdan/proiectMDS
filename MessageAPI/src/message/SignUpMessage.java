package message;

public class SignUpMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String password;

	public SignUpMessage() {
		
	}
	
	public SignUpMessage(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/*public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {

		System.out.println("din sign up server");

		// adaug, iau ID si fac o pereche (nume,id) dupa care setez conexiunile
		// si trimit mai departe

		// aici e null cand mesajul e de la server la server

		if (!ServerThread.isTakken(username)) {
			manager = new ManageUsers();
			user = new User(username, password, null, null, null, null, null, null, null);
			long id = manager.addUser(user);
			usernameId = new MyPair();
			usernameId.setUsername(username);
			usernameId.setUserId(id);
			ServerThread.addUserId(usernameId);
			// o lista cu id-ul userilor inregistrati in sesiunea curenta
			user = null;
			Message msg = new SignUpSuccesfullMessage();
			msg.setConnectionOfSender(null);
			msg.setConnectionOfReceiver(connectionOfSender);
			MessageThread.addToQueueMess(msg);
			System.out.println("sunt pe cale sa trimit un signUpSuccesfull");
			// apelez cu receiver / sender pentru ca acu se schimba sensul de
			// trimitere

		} else {
			Message msg = new SignUpUnsuccesfullMessage();
			msg.setConnectionOfSender(null);
			msg.setConnectionOfReceiver(connectionOfSender);
			MessageThread.addToQueueMess(msg);
		}

	}*/

}