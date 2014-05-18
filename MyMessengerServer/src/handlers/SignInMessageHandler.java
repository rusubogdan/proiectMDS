package handlers;

import java.util.ArrayList;
import java.util.List;

import threads.ServerThread;

import com.entities.Friend;
import com.entities.User;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import message.ListOfFriendsMessage;
import message.Message;
import message.SignInMessage;
import message.SignInSuccesfullMessage;
import message.SignInUnsuccesfullMessage;
import message.UserSignedInMessage;

public class SignInMessageHandler implements IMessageHandler {

	private Connection connection;

	public SignInMessageHandler(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof SignInMessage)) {
			throw new IllegalArgumentException("Illegal message type");
		}

		SignInMessage signInMessage = (SignInMessage) message;
		ManageUsers manageUsers = new ManageUsers();
		User user = manageUsers.getUser(signInMessage.getUser());

		if (user == null) {
			handleLoginFailed();
		} else {
			if (signInMessage.getPassword().equals(user.getUserPassword())) {
				connection.setUsername(signInMessage.getUser());
				ServerThread.addToOnlineUsers(signInMessage.getUser());

				Message signInSuccessfulMessage = new SignInSuccesfullMessage();
				connection.addToQueueConnection(signInSuccessfulMessage);

				ListOfFriendsMessage listOfFriendsMessage = new ListOfFriendsMessage();
				List<String> friends = new ArrayList<>();
				for (Friend friend : user.friends) {
					if (ServerThread.userOnline(friend.getFriend().getUsername())) {
						friends.add(friend.getFriend().getUsername());
					}
				}
				listOfFriendsMessage.setFriendsByName(friends);
				connection.addToQueueConnection(listOfFriendsMessage);

				UserSignedInMessage userSignedInMessage = new UserSignedInMessage();
				userSignedInMessage.setSignedInUser(signInMessage.getUser());
				Connection friendConnection;
				for (Friend friend : user.friendOf) {
					friendConnection = ServerThread.getConnectionByUsername(friend
							.getUser().getUsername());
					if (friendConnection != null) {
						friendConnection.addToQueueConnection(userSignedInMessage);
					}
				}
			} else {
				handleLoginFailed();
			}
		}
	}

	private void handleLoginFailed() {
		SignInUnsuccesfullMessage signInUnsuccesfullMessage = new SignInUnsuccesfullMessage();
		connection.addToQueueConnection(signInUnsuccesfullMessage);

		// TODO KILL CONNECTION
	}
}
