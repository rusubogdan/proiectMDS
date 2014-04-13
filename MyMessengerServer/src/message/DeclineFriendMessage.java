package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class DeclineFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User possiblyFriend;
	private Connection connection;
	
	public DeclineFriendMessage(User user, User possiblyFriend) {
		this.user = user;
		this.possiblyFriend = possiblyFriend;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}
	
	public User getPossibleyFriend() {
		return possiblyFriend;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}
	
}
