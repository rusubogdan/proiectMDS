package handlers;

import graphicInterfaces.AppHandler;
import message.Message;
import message.ServerHasBeenClosedMessage;

public class ServerHasBeenClosedMessageHandler implements IMessageHandler {

	private AppHandler appHandler;
	
	public ServerHasBeenClosedMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		if(!(message instanceof ServerHasBeenClosedMessage)) {
			throw new IllegalArgumentException("Illegal message type: " + message.getClass().getName());
		}
		
		appHandler.serverHasBeenClosed();
		
	}

}
