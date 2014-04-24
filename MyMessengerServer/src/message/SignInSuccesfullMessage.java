package message;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class SignInSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
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

	public SignInSuccesfullMessage() {

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
