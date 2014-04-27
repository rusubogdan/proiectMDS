package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class UnfriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User friend;

	public UnfriendMessage(User user, User friend) {
		this.user = user;
		this.friend = friend;
	}

	public User getUser() {
		return user;
	}

	public void interactOnServer() {

	}

	public void interactOnClient(ChatWindow chatWindow) {

	}

	public User getFriend() {
		return friend;
	}

}
