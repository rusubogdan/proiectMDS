package handlers;

import graphicInterfaces.AppHandler;
import message.Message;

public class SignInUnsuccesfullMessageHandler implements IMessageHandler {

	private AppHandler appHandler;

	public SignInUnsuccesfullMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

	@Override
	public void handleMessage(Message message) {
		if(!(message instanceof SignInUnsuccesfullMessageHandler)) {
			throw new IllegalArgumentException("Illegal message type: " + message.getClass().getName());
		}
		
		appHandler.signInUnsuccesfully();
		
	}

}
