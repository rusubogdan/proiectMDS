package message;

import graphicInterfaces.ChatWindow;

public class ServerHasBeenClosedMessage implements Message{

	private static final long serialVersionUID = 1L;
	private ChatWindow chatWindow;

	public void interactOnClient() {
		System.out.println("SERVERUL A FOST INCHIS");
		chatWindow.listOfUsersWindow.frame.dispose();
		chatWindow.disconnectFromServer();
		ChatWindow.main(null); 
	}

	public void setChatWindow(ChatWindow chatWindow) {
		
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}

}
