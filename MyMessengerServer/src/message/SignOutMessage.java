package message;

import threads.ServerThread;

import com.entities.User;

import graphicInterfacesServer.Connection;

public class SignOutMessage implements Message {
	// ca si server vreau sa stiu pe cine deconectez
	// sign out-ul poate fi doar cu succes; eventuala exceptie se trateaza in
	// server

	private static final long serialVersionUID = 1L;
	private transient Connection connectionOfSender;
	private transient Connection connectionOfReceiver;
	private String username;

	public String getName() {
		return this.username;
	}
	
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


	public void interactOnServer(Connection connectionOfSender,
			Connection connectionOfReceiver) {
		User user = connectionOfSender.getUser();
		ServerThread.removeFromOnlineUsersQueue(user);
	}
	
	public void interactOnServer() {
		User user = connectionOfSender.getUser();
		ServerThread.removeFromOnlineUsersQueue(user);
	}

	public void interactOnClient() {

	}

}