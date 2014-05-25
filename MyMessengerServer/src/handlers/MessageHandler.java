package handlers;

import message.AddFriendMessage;
import message.ChatMessage;
import message.Message;
import message.RequestFriendInfo;
import message.SignInMessage;
import message.SignOutMessage;
import message.SignUpMessage;
import message.UpdateUserInfo;
import graphicInterfacesServer.Connection;

public class MessageHandler {

	private MessageHandler() {
	}

	public static void handleMessage(Message message, Connection connection) {
		IMessageHandler messageHandler = null;

		if (message instanceof SignInMessage) {
			messageHandler = new SignInMessageHandler(connection);
		}

		if (message instanceof ChatMessage) {
			messageHandler = new ChatMessageHandler();
		}

		if (message instanceof SignUpMessage) {
			messageHandler = new SignUpMessageHandler(connection);
		}
		if (message instanceof AddFriendMessage) {
			messageHandler = new AddFriendMessageHandler(connection);
		}
		if (message instanceof SignOutMessage) {
			messageHandler = new SignOutMessageHandler(connection);
		}
		if (message instanceof RequestFriendInfo) {
			messageHandler = new RequestFriendInfoHandler(connection);
		}
		if (message instanceof UpdateUserInfo) {
			messageHandler = new UpdateUserInfoHandler(connection);
		}

		messageHandler.handleMessage(message);
	}
}
