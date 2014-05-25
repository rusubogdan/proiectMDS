package handlers;

import graphicInterfaces.AppHandler;
import message.Message;
import message.RequestedFriendInfo;

public class RequestedFriendInfoHandler implements IMessageHandler {

	private AppHandler appHandler;
	
	public RequestedFriendInfoHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
	
	public void handleMessage(Message message) {
		if(!(message instanceof RequestedFriendInfo))
			throw new IllegalArgumentException("Illegal arg ex" + message.getClass().getName());
	
	RequestedFriendInfo requestedFriendInfo = (RequestedFriendInfo)message;
	
	appHandler.showFriendInfo(requestedFriendInfo);
	
	}

}
