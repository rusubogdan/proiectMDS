package message;

import graphicInterfaces.ChatWindow;

import java.util.List;

import com.entities.User;

public class ChatMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private String message;
	private List<User> friends;

	// din client catre server, apoi din server catre prietenii user-ului
	public ChatMessage(User user, String message, List<User> friends) {
		this.user = user;
		this.message = message;
		this.friends = friends;
	}

	public void interactOnServer() {

	}

	public void interactOnClient(ChatWindow chatWindow) {

	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public List<User> getFriends() {
		return friends;
	}
}
