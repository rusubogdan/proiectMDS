package message;

import graphicInterfaces.ChatWindow;

import java.util.ArrayList;
import java.util.List;

public class ChatMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String message;
	private ArrayList<String> friends;
	private ChatWindow chatWindow;

	public ChatMessage(String user, String message, ArrayList<String> friends) {
		this.user = user;
		this.message = message;
		this.friends = friends;
	}

	public void interactOnClient() {
		System.out.println(message);
	}

	public void interactOnServer() {

	}

	public void interactOnClient(ChatWindow chatWindow) {

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

	public void setChatWindow(ChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}
}
