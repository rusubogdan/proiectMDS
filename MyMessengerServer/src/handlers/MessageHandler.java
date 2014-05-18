package handlers;

import message.AddFriendMessage;
import message.ChatMessage;
import message.Message;
import message.SignInMessage;
import message.SignUpMessage;
import graphicInterfacesServer.Connection;

public class MessageHandler {

	private MessageHandler(){}
	
	public static void handleMessage(Message message, Connection connection) {
		IMessageHandler messageHandler = null;
		
		if(message instanceof SignInMessage) {
			messageHandler = new SignInMessageHandler(connection);
		}
		
		if(message instanceof ChatMessage) {
			messageHandler = new ChatMessageHandler();
		}
		
		if(message instanceof SignUpMessage) {
			messageHandler = new SignUpMessageHandler(connection);
		}
		if (message instanceof AddFriendMessage) {
			messageHandler = new AddFriendMessageHandler(connection);
		}
		
		messageHandler.handleMessage(message);
	}
}
