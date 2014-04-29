package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class DeclineFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private User possiblyFriend;

	public DeclineFriendMessage(User user, User possiblyFriend) {
		this.user = user;
		this.possiblyFriend = possiblyFriend;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public void interactOnClient(ChatWindow chatWindow) {

	}

	public User getUser() {
		return user;
	}

	public User getPossibleyFriend() {
		return possiblyFriend;
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
