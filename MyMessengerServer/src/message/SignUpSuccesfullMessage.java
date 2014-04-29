package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class SignUpSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private transient Connection connectionOfSender = null;
	private transient Connection connectionOfReceiver = null;

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

	public SignUpSuccesfullMessage() {
	}

	public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {

	}

	public void interactOnClient() {
	}

	public User getUser() {
		return user;
	}

}
