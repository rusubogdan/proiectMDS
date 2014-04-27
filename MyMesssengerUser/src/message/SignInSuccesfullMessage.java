package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class SignInSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	
	public SignInSuccesfullMessage() {
		
	}

	public void interactOnServer() {

	}

	public void interactOnClient(ChatWindow chatWindow) {
		chatWindow.instantiate();
	}

	public User getUser() {
		return user;
	}

}
