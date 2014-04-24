package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class ConfirmFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User possiblyFriend;
	private Connection connectionOfReceiver;
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


	public ConfirmFriendMessage(User user, User possiblyFriend) {
		this.user = user;
		this.possiblyFriend = possiblyFriend;
	}

	public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

	public User getPossiblyFriend() {
		return possiblyFriend;
	}

}
