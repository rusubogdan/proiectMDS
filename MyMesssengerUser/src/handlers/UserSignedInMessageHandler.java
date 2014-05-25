package handlers;

import graphicInterfaces.AppHandler;
import graphicInterfaces.ListOfUsersWindow;
import message.Message;
import message.UserSignedInMessage;

public class UserSignedInMessageHandler implements IMessageHandler {

	@SuppressWarnings("unused")
	private AppHandler appHandler;

	public UserSignedInMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof UserSignedInMessage)) {
			throw new IllegalArgumentException();
		}

		UserSignedInMessage userSignedInMessage = (UserSignedInMessage) message;
		String userSignedIn = userSignedInMessage.getSignedInUser();

		ListOfUsersWindow.addUserToList(userSignedIn);

	}

}
