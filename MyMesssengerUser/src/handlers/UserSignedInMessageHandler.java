package handlers;

import graphicInterfaces.AppHandler;
import message.Message;

public class UserSignedInMessageHandler implements IMessageHandler {

	private AppHandler appHandler;
	
	public UserSignedInMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

	@Override
	public void handleMessage(Message message) {
		
	}

}
