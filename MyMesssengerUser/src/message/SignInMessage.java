package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class SignInMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private String username;
	private String password;

	public SignInMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void interactOnServer() {
		System.out.println("sunt pe server");
	}

	public void interactOnClient(ChatWindow chatWindow) {

	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
