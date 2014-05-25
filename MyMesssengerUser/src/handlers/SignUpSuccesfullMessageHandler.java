package handlers;

import graphicInterfaces.AppHandler;
import message.Message;
import message.SignUpSuccesfullMessage;

public class SignUpSuccesfullMessageHandler implements IMessageHandler {

	private AppHandler appHandler;
	
	public SignUpSuccesfullMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		if(!(message instanceof SignUpSuccesfullMessage)) {
			throw new IllegalArgumentException("Illegal argument exception");
		}
		
		appHandler.signUpSuccesfully();
		appHandler.disconnectFromServer();
	}

}
