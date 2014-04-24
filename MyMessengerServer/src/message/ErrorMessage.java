package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class ErrorMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private String message;
	private Connection connection;
	private Connection connectionOfSender;

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

	private Connection connectionOfReceiver;
	
	
	public ErrorMessage(User user, String message) {
		// prin campul message trimit = user/friend inexistent
		this.user = user;
		this.message = message;
	}

	public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}
	
	public String getMessage() {
		return message;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}
