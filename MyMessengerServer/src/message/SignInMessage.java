package message;

import java.util.List;

import threads.MessageThread;
import threads.ServerThread;
import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;

import com.entities.User;

public class SignInMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private String username;
	private transient Connection connectionOfReceiver;
	private String password;
	private transient Connection connectionOfSender;

	public Connection getConnectionOfSender() {
		return connectionOfSender;
	}

	public void setConnectionOfSender(Connection connectionOfSender) {
		this.connectionOfSender = connectionOfSender;
	}

	public Connection getConnectionOfReceiver() {
		return connectionOfReceiver;
	}

	public void setConnectionOfReceiver(Connection connectionOfReceiver) {
		this.connectionOfReceiver = connectionOfReceiver;
	}

	public SignInMessage(String username, String password, Connection connectionOfSender,
			Connection connectionOfReceiver) {
		this.username = username;
		this.password = password;
		this.connectionOfSender = connectionOfSender;
		this.connectionOfReceiver = connectionOfReceiver;
	}

	public void interactOnServer(Connection c1, Connection c2) {

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

			//ServerThread.trigger();
			// se declanseaza trimiterea la toti userii lista celorlalti useri

		} else {
			Message msg = new SignInUnsuccesfullMessage(connectionOfReceiver,
					connectionOfSender);
			MessageThread.addToQueueMess(msg);
		}

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
