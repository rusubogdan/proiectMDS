package message;

import graphicInterfacesServer.Connection;

import java.util.List;

import threads.MessageThread;
import threads.ServerThread;

public class ChatMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String message;
	private List<String> friends;
	private transient Connection connectionOfSender;
	private transient Connection connectionOfReceiver;

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

	public ChatMessage(String user, String message, List<String> friends) {
		this.user = user;
		this.message = message;
		this.friends = friends;
	}

	public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {

		this.connectionOfReceiver = ServerThread.getConnectionByUsername(friends.get(0));
		MessageThread.addToQueueMess(this);

	}

	public void interactOnServer() {

	}

	public String getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setConnection(Connection connection) {
		this.connectionOfSender = connection;
	}

	public Connection getConnection() {
		return connectionOfSender;
	}
}
