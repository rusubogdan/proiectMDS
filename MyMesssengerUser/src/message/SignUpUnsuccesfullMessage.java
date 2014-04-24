package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class SignUpUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;

	public SignUpUnsuccesfullMessage() {
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {
		System.out.println("unsuccesfull on client");
		ChatWindow.clearFields();
	}

	public User getUser() {
		return user;
	}

}
