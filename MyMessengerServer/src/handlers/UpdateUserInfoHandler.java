package handlers;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;
import message.Message;
import message.UpdateUserInfo;

import com.entities.User;

public class UpdateUserInfoHandler implements IMessageHandler {

	private Connection connection;

	public UpdateUserInfoHandler(Connection connection) {
		this.connection = connection;
	}

	public void handleMessage(Message message) {
		if (!(message instanceof UpdateUserInfo)) {
			throw new IllegalArgumentException("Illegal arg ex"
					+ message.getClass().getName());
		}

		UpdateUserInfo updateUserInfo = (UpdateUserInfo) message;

		ManageUsers manager = new ManageUsers();

		User user = manager.getUser(updateUserInfo.getUser());

		if (user != null) {

			user.setJoinDate("");
			user.setMiddleName("");

			user.setFirstName(updateUserInfo.getFirstName());
			user.setLastName(updateUserInfo.getLastName());
			user.setMobileNumber(updateUserInfo.getMobileNumber());
			user.setHomePhoneNumber(updateUserInfo.getHomePhoneNumber());
			user.setAddress(updateUserInfo.getAddress());

			manager.updateUser(user);
			
		}

	}

}
