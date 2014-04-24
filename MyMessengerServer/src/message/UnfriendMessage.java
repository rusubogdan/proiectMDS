package message;

import graphicInterfacesServer.Connection;
import com.entities.User;

public class UnfriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User friend;
	private Connection connectionOfSender;
	private Connection connectionOfReceiver;

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

	public UnfriendMessage(User user, User friend) {
		this.user = user;
		this.friend = friend;
	}

	public User getUser() {
		return user;
	}

	public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {

	}

	public void interactOnClient() {

	}

	public User getFriend() {
		return friend;
	}

}
