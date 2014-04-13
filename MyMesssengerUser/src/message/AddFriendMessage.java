package message;

import com.entities.User;

public class AddFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User friend;

	protected AddFriendMessage(User user, User friends) {
		this.user = user;
		this.friend = friends;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

	public User getFriend() {
		return friend;
	}
}
