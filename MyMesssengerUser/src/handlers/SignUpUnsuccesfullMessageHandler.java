package handlers;

import graphicInterfaces.AppHandler;
import message.Message;
import message.SignUpUnsuccesfullMessage;

public class SignUpUnsuccesfullMessageHandler implements IMessageHandler {

	private AppHandler appHandler;

	public SignUpUnsuccesfullMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

	@Override
	public void handleMessage(Message message) {
		if(!(message instanceof SignUpUnsuccesfullMessage)) {
			throw new IllegalArgumentException("Illegal argument exception");
		}
		
		appHandler.signUpUnsuccesfully();
		appHandler.disconnectFromServer();
		
	}

}
