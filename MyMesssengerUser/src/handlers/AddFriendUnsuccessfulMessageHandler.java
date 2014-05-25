package handlers;

import graphicInterfaces.AppHandler;
import message.AddFriendUnsuccessfulMessage;
import message.Message;

public class AddFriendUnsuccessfulMessageHandler implements IMessageHandler {

	private AppHandler appHandler;

	public AddFriendUnsuccessfulMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof AddFriendUnsuccessfulMessage)) {
			throw new IllegalArgumentException("Illegal message type: "
					+ message.getClass().getName());
		}
		
		appHandler.addFriendUnuccesfully();
		
	}

}
