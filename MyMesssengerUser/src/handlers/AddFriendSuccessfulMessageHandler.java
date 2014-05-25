package handlers;

import graphicInterfaces.AppHandler;
import message.AddFriendSuccessfulMessage;
import message.Message;

public class AddFriendSuccessfulMessageHandler implements IMessageHandler {

	private AppHandler appHandler;

	public AddFriendSuccessfulMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof AddFriendSuccessfulMessage)) {
			throw new IllegalArgumentException("Illegal message type: "
					+ message.getClass().getName());
		}

		appHandler.addFriendSuccesfully();

	}

}
