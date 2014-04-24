package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class AddFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User friend;
	private Connection connectionOfSender;
	private Connection connectionOfReceiver;
	
	protected AddFriendMessage(User user, User friends) {
		this.user = user;
		this.friend = friends;
	}

	public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

	public User getFriend() {
		return friend;
	}

	public void setConnectionOfSender(Connection connection) {
		this.connectionOfSender = connection;
	}

	public Connection getConnectionOfSender() {
		return connectionOfSender;
	}

	public Connection getConnectionOfReceiver() {
		return connectionOfReceiver;
	}

	public void setConnectionOfReceiver(Connection connectionOfReceiver) {
		this.connectionOfReceiver = connectionOfReceiver;
	}
}
