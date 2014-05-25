package handlers;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import message.AddFriendMessage;
import message.AddFriendSuccessfulMessage;
import message.AddFriendUnsuccessfulMessage;
import message.Message;
import message.UserAlreadyFriend;
import message.UserSignedInMessage;
import threads.ServerThread;

import com.entities.Friend;
import com.entities.User;

public class AddFriendMessageHandler implements IMessageHandler {

	@SuppressWarnings("unused")
	private Connection connection;

	public AddFriendMessageHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof AddFriendMessage)) {
			throw new IllegalArgumentException("Illegal message type: "
					+ message.getClass().getName());
		}

		AddFriendMessage addFriendMessage = (AddFriendMessage) message;
		ManageUsers manageUsers = new ManageUsers();

		User user = manageUsers.getUser(addFriendMessage.getUser());
		User friendOfUser = manageUsers.getUser(addFriendMessage.getFriend());

		boolean userExists = false;

		if (friendOfUser != null)
			userExists = true;

		boolean alreadyFound = false;

		if (userExists) {
			for (Friend friend : user.getFriends()) {
				if (friend.getFriend().equals(addFriendMessage.getFriend())) {
					alreadyFound = true;

					UserAlreadyFriend userAlreadyFriend = new UserAlreadyFriend(
							addFriendMessage.getUser(), addFriendMessage.getFriend());
					Connection connection = ServerThread
							.getConnectionByUsername(addFriendMessage.getUser());
					connection.addToQueueConnection(userAlreadyFriend);

					break;
				}
			}
		} else {
			Connection connection1 = ServerThread
					.getConnectionByUsername(addFriendMessage.getUser());
			if (connection1 != null)
				connection1.addToQueueConnection(new AddFriendUnsuccessfulMessage());
		}

		if (!alreadyFound && userExists) {

			manageUsers.setAsFriends(addFriendMessage.getUser(),
					addFriendMessage.getFriend());

			Connection connection = ServerThread.getConnectionByUsername(addFriendMessage
					.getUser());
			if (connection != null)
				connection.addToQueueConnection(new AddFriendSuccessfulMessage());

			connection = ServerThread.getConnectionByUsername(addFriendMessage
					.getFriend());

			UserSignedInMessage userSignedInMessage = new UserSignedInMessage();
			userSignedInMessage.setSignedInUser(addFriendMessage.getUser());

			if (connection != null) {
				connection.addToQueueConnection(userSignedInMessage);
				UserSignedInMessage userSignedInMessage2 = new UserSignedInMessage();
				userSignedInMessage2.setSignedInUser(addFriendMessage.getFriend());
				connection = ServerThread.getConnectionByUsername(addFriendMessage
						.getUser());
				connection.addToQueueConnection(userSignedInMessage2);
			} else {
				//userul nu este conectat la momentul actual
			}
		}
	}

}
