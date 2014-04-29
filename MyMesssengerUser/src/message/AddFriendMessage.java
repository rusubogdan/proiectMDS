package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class AddFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User friend;

	protected AddFriendMessage(User user, User friends) {
		this.user = user;
		this.friend = friends;
	}

	public void interactOnClient() {

	}

	public void interactOnServer() {

	}

	public void interactOnClient(ChatWindow chatWindow) {

	}

	public User getUser() {
		return user;
	}

	public User getFriend() {
		return friend;
	}

	@Override
	public void setChatWindow(ChatWindow chatWindow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChatWindow getChatWindow() {
		// TODO Auto-generated method stub
		return null;
	}
}
