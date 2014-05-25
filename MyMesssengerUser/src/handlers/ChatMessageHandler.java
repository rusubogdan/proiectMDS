package handlers;

import graphicInterfaces.AppHandler;
import graphicInterfaces.ListOfUsersWindow;
import message.ChatMessage;
import message.Message;

public class ChatMessageHandler implements IMessageHandler {

	@SuppressWarnings("unused")
	private AppHandler appHandler;

	public ChatMessageHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

	@Override
	public void handleMessage(Message message) {
		if (!(message instanceof ChatMessage)) {
			throw new IllegalArgumentException("Illegal message type: "
					+ message.getClass().getName());
		}

		ChatMessage chatMessage = (ChatMessage) message;
		
		ListOfUsersWindow.addMessageToMessageQueue(chatMessage);
		

		
		
	}

}
