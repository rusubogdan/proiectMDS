package handlers;

import graphicInterfaces.AppHandler;
import message.Message;

public class SignUpUnsuccesfullMessageHandler implements IMessageHandler {

	private AppHandler appHandler;

	public SignUpUnsuccesfullMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		
	}

}
