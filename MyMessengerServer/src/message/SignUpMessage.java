package message;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import graphicInterfacesServer.MyPair;
import threads.MessageThread;
import threads.ServerThread;

import com.entities.User;

public class SignUpMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private String username;
	private String password;
	private ManageUsers manager;
	private transient Connection connectionOfSender;
	private transient Connection connectionOfReceiver;

	public Connection getConnectionOfSender() {
		return connectionOfSender;
	}

	public void setConnectionOfSender(Connection connectionOfSender) {
		 this.connectionOfSender = connectionOfSender;
	}

	private MyPair usernameId;

	public SignUpMessage(String username, String password,
			Connection connectionOfSeConnection, Connection connectionOfReceiver) {
		this.username = username;
		this.password = password;
	}

	public void interactOnServer(Connection connectionOfSender,
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
			// apelez cu receiver / sender pentru ca acu se schimba sensul de
			// trimitere

		} else {
			Message msg = new SignUpUnsuccesfullMessage();
			msg.setConnectionOfSender(null);
			msg.setConnectionOfReceiver(connectionOfSender);
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

	public Connection getConnectionOfReceiver() {
		return connectionOfReceiver;
	}

	public void setConnectionOfReceiver(Connection connectionOfReceiver) {
		 this.connectionOfReceiver = connectionOfReceiver;
	}
}
