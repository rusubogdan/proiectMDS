package handlers;

import graphicInterfaces.AppHandler;
import message.Message;
import message.UserAlreadyFriend;

public class UserAlreadyFriendHandler implements IMessageHandler {

	
	private AppHandler appHandler;
	
	
	public UserAlreadyFriendHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	@Override
	public void handleMessage(Message message) {
		if( !(message instanceof UserAlreadyFriend)) {
			throw new IllegalArgumentException("Illegal argument exception " + message.getClass().getName());
		}
		
		appHandler.userAlreadyFriend();
		
	}

}
