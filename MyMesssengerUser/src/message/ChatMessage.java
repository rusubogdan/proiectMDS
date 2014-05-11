package message;

import graphicInterfaces.AppHandler;
import graphicInterfaces.ListOfUsersWindow;

import java.util.ArrayList;
import java.util.List;

public class ChatMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String message;
	private ArrayList<String> friends;
	@SuppressWarnings("unused")
	private transient AppHandler appHandler;

	public ChatMessage(String user, String message, ArrayList<String> friends) {
		this.user = user;
		this.message = message;
		this.friends = friends;
	}

	public void interactOnClient() {
		System.out.println("La interact on client" + user + " " + message + " " + friends.get(0));
		ListOfUsersWindow.addMessageToMessageQueue(this);
	}

	public String getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}
}
