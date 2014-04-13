package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class SignInUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private Connection connection;

	public SignInUnsuccesfullMessage() {
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}
