package handlers;

import graphicInterfaces.AppHandler;
import message.Message;

public class UserSignedOutMessageHandler implements IMessageHandler {

	private AppHandler appHandler;
	
	public UserSignedOutMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		
	}

}
