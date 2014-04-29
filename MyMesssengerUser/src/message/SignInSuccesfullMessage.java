package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class SignInSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private ChatWindow chatWindow = null;

	public SignInSuccesfullMessage() {

	}

	public void interactOnServer() {

	}

	public void interactOnClient() {
		this.chatWindow.instantiate();
		this.chatWindow.closeWindow();
	}

	public void interactOnClient(ChatWindow chatWindow) {
	}

	public User getUser() {
		return user;
	}

	public void setChatWindow(ChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}

}
