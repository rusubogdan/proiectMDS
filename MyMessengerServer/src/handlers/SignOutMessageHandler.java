package handlers;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import java.util.Set;
import message.Message;
import message.SignOutMessage;
import message.UserSignedOutMessage;
import threads.ServerThread;
import com.entities.Friend;
import com.entities.User;

public class SignOutMessageHandler implements IMessageHandler {

	Connection connection;

	public SignOutMessageHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof SignOutMessage)) {
			throw new IllegalArgumentException("Illegal message type: "
					+ message.getClass().getName());
		}

		System.out.println("A primit un mesaj de signout");

		SignOutMessage signOutMessage = (SignOutMessage) message;
		ManageUsers manageUsers = new ManageUsers();
		User user = manageUsers.getUser(signOutMessage.getUser());
		Set<Friend> friends = user.getFriends();

		Connection friendConnection;
		
		UserSignedOutMessage userSignedOutMessage = new UserSignedOutMessage();
		userSignedOutMessage.setSignedOutUser(signOutMessage.getUser());

		for (Friend friend : friends) {
			friendConnection = ServerThread.getConnectionByUsername(friend.getFriend()
					.getUsername());
			if (friendConnection != null) {
				friendConnection.addToQueueConnection(userSignedOutMessage);
			}
		}

		// KILL CONNECTION or not
	}
}
