package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class AddFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User friend;
	private Connection connection;

	protected AddFriendMessage(User user, User friends) {
		this.user = user;
		this.friend = friends;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
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
