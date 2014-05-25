package handlers;

import graphicInterfaces.AppHandler;
import graphicInterfaces.ListOfUsersWindow;
import message.Message;
import message.UserSignedOutMessage;

public class UserSignedOutMessageHandler implements IMessageHandler {

	@SuppressWarnings("unused")
	private AppHandler appHandler;
	
	public UserSignedOutMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof UserSignedOutMessage)) {
			throw new IllegalArgumentException();
		}

		UserSignedOutMessage userSignedOutMessage = (UserSignedOutMessage) message;
		String userSignedOut = userSignedOutMessage.getSignedOutUser();
		
		ListOfUsersWindow.removeUserFromList(userSignedOut);
	}

}
