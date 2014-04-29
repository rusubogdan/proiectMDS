package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class ErrorMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;
	private String message;

	public ErrorMessage(User user, String message) {
		// prin campul message trimit = user/friend inexistent
		this.user = user;
		this.message = message;
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

	public String getMessage() {
		return message;
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
