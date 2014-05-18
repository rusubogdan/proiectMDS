package handlers;

import threads.ServerThread;
import graphicInterfacesServer.Connection;
import message.ChatMessage;
import message.ErrorMessage;
import message.Message;

public class ChatMessageHandler implements IMessageHandler {

	@Override
	public void handleMessage(Message message) {
		if(!(message instanceof ChatMessage)) {
			throw new IllegalArgumentException("Illegal message type: " + message.getClass().getName());
		}
		
		ChatMessage chatMessage = (ChatMessage) message;
		Connection connection = ServerThread.getConnectionByUsername(chatMessage.getFriend());
		if(connection != null) {
			connection.addToQueueConnection(chatMessage);
		} else {
			connection = ServerThread.getConnectionByUsername(chatMessage.getUser());
			connection.addToQueueConnection(new ErrorMessage("There was a problem sending your message."));
		}
	}

}
