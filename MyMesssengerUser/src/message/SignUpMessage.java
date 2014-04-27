package message;

import graphicInterfaces.ChatWindow;
import graphicInterfaces.ManageUsers;

import com.entities.User;

public class SignUpMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private String username;
	private String password;
	private ManageUsers manager;

	public SignUpMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void interactOnServer() {
		System.out.println("din sign up user");
		manager = new ManageUsers();
		manager.addUser(username, password, null, null, null, null, null, null, null);
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
