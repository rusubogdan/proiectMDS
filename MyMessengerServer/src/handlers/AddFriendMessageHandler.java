package handlers;

import threads.ServerThread;
import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import message.AddFriendMessage;
import message.AddFriendSuccessfulMessage;
import message.Message;
import message.UserSignedInMessage;

public class AddFriendMessageHandler implements IMessageHandler {

	private Connection connection;
	
	public AddFriendMessageHandler(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void handleMessage(Message message) {
		if(!(message instanceof AddFriendMessage)) {
			throw new IllegalArgumentException("Illegal message type: " + message.getClass().getName());
		}
		
		AddFriendMessage addFriendMessage = (AddFriendMessage)message;
		ManageUsers manageUsers = new ManageUsers();
		manageUsers.setAsFriends(addFriendMessage.getUser(), addFriendMessage.getFriend());
		
		Connection connection = ServerThread.getConnectionByUsername(addFriendMessage.getUser());
		connection.addToQueueConnection(new AddFriendSuccessfulMessage());
		
		connection = ServerThread.getConnectionByUsername(addFriendMessage.getFriend());
		UserSignedInMessage userSignedInMessage = new UserSignedInMessage();
		userSignedInMessage.setSignedInUser(addFriendMessage.getUser());
		if(connection != null) {
			connection.addToQueueConnection(userSignedInMessage);
			userSignedInMessage.setSignedInUser(addFriendMessage.getFriend());
			connection = ServerThread.getConnectionByUsername(addFriendMessage.getUser());
			connection.addToQueueConnection(userSignedInMessage);
		}
	}

}
