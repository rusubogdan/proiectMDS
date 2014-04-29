package message;

import graphicInterfacesServer.Connection;
import com.entities.User;

public class SignInSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
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

	public SignInSuccesfullMessage(Connection connectionOfSender,
			Connection connectionOfReceiver) {
		this.connectionOfSender = connectionOfSender;
		this.connectionOfReceiver = connectionOfReceiver;

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
