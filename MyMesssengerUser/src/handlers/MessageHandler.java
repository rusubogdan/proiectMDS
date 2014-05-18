package handlers;

import graphicInterfaces.AppHandler;
import message.AddFriendSuccessfulMessage;
import message.AddFriendUnsuccessfulMessage;
import message.ChatMessage;
import message.ListOfFriendsMessage;
import message.Message;
import message.ServerHasBeenClosedMessage;
import message.SignInSuccesfullMessage;
import message.SignInUnsuccesfullMessage;
import message.SignUpSuccesfullMessage;
import message.SignUpUnsuccesfullMessage;
import message.UserSignedInMessage;
import message.UserSignedOutMessage;

public class MessageHandler {

	private MessageHandler() {
	}

	public static void handleMessage(Message message, AppHandler appHandler) {
		IMessageHandler messageHandler = null;

		if (message instanceof SignInSuccesfullMessage) {
			messageHandler = new SignInSuccesfullMessageHandler(appHandler);
		}
		if (message instanceof SignInUnsuccesfullMessage) {
			messageHandler = new SignInUnsuccesfullMessageHandler(appHandler);
		}
		if (message instanceof SignUpSuccesfullMessage) {
			messageHandler = new SignUpSuccesfullMessageHandler(appHandler);
		}
		if (message instanceof SignUpUnsuccesfullMessage) {
			messageHandler = new SignUpUnsuccesfullMessageHandler(appHandler);
		}
		if (message instanceof AddFriendSuccessfulMessage) {
			messageHandler = new AddFriendSuccessfulMessageHandler(appHandler);
		}
		if (message instanceof ChatMessage) {
			messageHandler = new ChatMessageHandler(appHandler);
		}
		if (message instanceof AddFriendUnsuccessfulMessage) {
			messageHandler = new AddFriendUnsuccessfulMessageHandler(appHandler);
		}
		if (message instanceof UserSignedInMessage) {
			messageHandler = new UserSignedInMessageHandler(appHandler);
		}
		if (message instanceof UserSignedOutMessage) {
			messageHandler = new UserSignedOutMessageHandler(appHandler);
		}
		if (message instanceof ListOfFriendsMessage) {
			messageHandler = new ListOfFriendsMessageHandler(appHandler);
		}
		if (message instanceof ServerHasBeenClosedMessage) {
			messageHandler = new ServerHasBeenClosedMessageHandler(appHandler);
		}

		messageHandler.handleMessage(message);
	}
}
