package handlers;

import com.entities.User;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import message.Message;
import message.SignUpMessage;
import message.SignUpSuccesfullMessage;
import message.SignUpUnsuccesfullMessage;

public class SignUpMessageHandler implements IMessageHandler {

	private Connection connection;

	public SignUpMessageHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof SignUpMessage)) {
			throw new IllegalArgumentException("Illegal message type");
		}

		SignUpMessage signUpMessage = (SignUpMessage) message;
		ManageUsers manageUsers = new ManageUsers();
		User newUser = new User();
		newUser.setUsername(signUpMessage.getUser());
		newUser.setUserPassword(signUpMessage.getPassword());
		long result = manageUsers.addUser(newUser);

		if (result != 0) {
			connection.addToQueueConnection(new SignUpSuccesfullMessage());
		} else {
			connection.addToQueueConnection(new SignUpUnsuccesfullMessage());
		}
		
		connection.cancel();
		// TODO KILL CONNECTION
	}

}
