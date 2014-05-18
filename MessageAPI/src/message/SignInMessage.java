package message;

public class SignInMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	
	public SignInMessage() {
		
	}
	
	public SignInMessage(String user, String password) {
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

/*	public void interactOnServer(Connection c1, Connection c2) {

		System.out.println("Sunt in SignIn pe server");

		ManageUsers manager = new ManageUsers();
		List<User> list = null;
		Boolean hasBeenFind = false;
		list = manager.getListOfUsers();

		for (User user : list) {
			if (user.getUsername().equals(username)
					&& user.getUserPassword().equals(password)) {
				this.user = user;
				System.out.println("am gasit userul in baza de date");
				hasBeenFind = true;
				break;
			}
		}

		if (hasBeenFind) {
			connectionOfSender.setUser(null);
			connectionOfSender.setUser(user);
			// acum conexiunea este legatura dintre un user existent in baza de
			// date si restul conexiunilor

			Message msg = new SignInSuccesfullMessage(connectionOfReceiver,
					connectionOfSender);

			MessageThread.addToQueueMess(msg);

			ServerThread.addToOnlineUsersQueue(user);
			// adaug userul la useri online

			System.out.println("\nDin SignIn am trimis catre "
					+ connectionOfSender.getUser().getUsername() + "un SignInSuccesfull");

//			 ServerThread.trigger();
			// se declanseaza trimiterea la toti userii lista celorlalti useri

		} else {
			Message msg = new SignInUnsuccesfullMessage(connectionOfReceiver,
					connectionOfSender);
			MessageThread.addToQueueMess(msg);
		}

	}*/

	
}