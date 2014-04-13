package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class UnfriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User friend;
	private Connection connection; 

	public UnfriendMessage(User user, User friend) {
		this.user = user;
		this.friend = friend;
	}

	public User getUser() {
		return user;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getFriend() {
		return friend;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}
