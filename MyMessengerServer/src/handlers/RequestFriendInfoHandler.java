package handlers;

import com.entities.User;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import message.Message;
import message.RequestFriendInfo;
import message.RequestedFriendInfo;

public class RequestFriendInfoHandler implements IMessageHandler {

	private Connection connection;

	public RequestFriendInfoHandler(Connection connection) {
		this.connection = connection;
	}

	public void handleMessage(Message message) {
		if (!(message instanceof RequestFriendInfo)) {
			throw new IllegalArgumentException("Illegal arg ex "
					+ message.getClass().getName());
		}

		RequestFriendInfo requestFriendInfo = (RequestFriendInfo) message;

		String username = requestFriendInfo.getUser();

		ManageUsers manager = new ManageUsers();

		User user = manager.getUser(username);

		RequestedFriendInfo requestedFriendInfo = new RequestedFriendInfo();

		requestedFriendInfo.setUser(requestFriendInfo.getUser());
		
		if (user.getFirstName() != null)
			requestedFriendInfo.setFirstName(user.getFirstName());
		else
			requestedFriendInfo.setFirstName("");
		if (user.getLastName() != null)
			requestedFriendInfo.setLastName(user.getLastName());
		else
			requestedFriendInfo.setLastName("");
		if (user.getMobileNumber() != null)
			requestedFriendInfo.setMobileNumber(user.getMobileNumber());
		else
			requestedFriendInfo.setMobileNumber("");
		if (user.getHomePhoneNumber() != null)
			requestedFriendInfo.setHomePhoneNumber(user.getHomePhoneNumber());
		else
			requestedFriendInfo.setHomePhoneNumber("");
		if (user.getAddress() != null)
			requestedFriendInfo.setAddress(user.getAddress());
		else
			requestedFriendInfo.setAddress("");

		if (connection != null)
			connection.addToQueueConnection(requestedFriendInfo);

	}

}
