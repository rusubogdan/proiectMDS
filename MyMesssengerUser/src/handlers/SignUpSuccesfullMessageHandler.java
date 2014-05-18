package handlers;

import graphicInterfaces.AppHandler;
import message.Message;

public class SignUpSuccesfullMessageHandler implements IMessageHandler {

	private AppHandler appHandler;
	
	public SignUpSuccesfullMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		
	}

}
