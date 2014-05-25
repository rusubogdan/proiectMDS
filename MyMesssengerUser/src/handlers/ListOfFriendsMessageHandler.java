package handlers;

import graphicInterfaces.AppHandler;
import graphicInterfaces.ListOfUsersWindow;
import message.ListOfFriendsMessage;
import message.Message;

public class ListOfFriendsMessageHandler implements IMessageHandler {

	@SuppressWarnings("unused")
	private AppHandler appHandler;
	
	public ListOfFriendsMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof ListOfFriendsMessage)) {
			throw new IllegalArgumentException("Illegal message type: "
					+ message.getClass().getName());
		}
		
		ListOfFriendsMessage listOfFriendsMessage = (ListOfFriendsMessage)message;
		
		ListOfUsersWindow.addUsersToList(listOfFriendsMessage.getFriendsByName());
		
	}

}
