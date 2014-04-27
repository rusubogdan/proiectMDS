package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class ConfirmFriendMessage implements Message{

	private static final long serialVersionUID = 1L;
	private User user;
	private User possiblyFriend;

	public ConfirmFriendMessage(User user, User possiblyFriend) {
		this.user = user;
		this.possiblyFriend = possiblyFriend;
	}

	public void interactOnServer() {

	}

	public void interactOnClient(ChatWindow chatWindow) {

	}

	public User getUser() {
		return user;
	}

	public User getPossiblyFriend() {
		return possiblyFriend;
	}

}
