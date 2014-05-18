package handlers;

import graphicInterfaces.AppHandler;
import message.Message;
import message.SignInSuccesfullMessage;

public class SignInSuccesfullMessageHandler implements IMessageHandler {

	private AppHandler appHandler;

	public SignInSuccesfullMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		if(!(message instanceof SignInSuccesfullMessage)) {
			throw new IllegalArgumentException("Illegal message type: " + message.getClass().getName());
		}
		
		appHandler.signInSuccesfully();
		
	}

}
