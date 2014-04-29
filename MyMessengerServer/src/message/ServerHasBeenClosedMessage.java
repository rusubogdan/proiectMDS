package message;

import graphicInterfacesServer.Connection;

public class ServerHasBeenClosedMessage implements Message {

	private static final long serialVersionUID = 1L;
	private transient Connection connectionOfSender;
	private transient Connection connectionOfReceiver;

	public void interactOnServer(Connection connection, Connection connection2) {
	}

	public Connection getConnectionOfSender() {
		return connectionOfSender;
	}

	public Connection getConnectionOfReceiver() {
		return connectionOfReceiver;
	}

	public void setConnectionOfSender(Connection connection) {
		connectionOfSender = connection;
	}

	public void setConnectionOfReceiver(Connection connection) {
		connectionOfReceiver = connection;
	}

}
