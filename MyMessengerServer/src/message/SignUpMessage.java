package message;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import graphicInterfacesServer.MessagesPanel;
import graphicInterfacesServer.MyPair;

import com.entities.User;

public class SignUpMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private String username;
	private String password;
	private ManageUsers manager;
	private Connection connection;
	private MyPair usernameId;

	public SignUpMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void interactOnServer() {
		System.out.println("din sign up server");

		if (!MessagesPanel.isTakken(username)) {
			manager = new ManageUsers();
			user = new User(username, password, null, null, null, null, null, null, null);
			long id = manager.addUser(user);
			usernameId = new MyPair();
			usernameId.setUsername(username);
			usernameId.setUserId(id);
			MessagesPanel.addUserId(usernameId);
			user = null;
		} else {
			Message msg = new SignUpUnsuccesfullMessage();
			connection.addToQueueConnection(msg);
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

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
