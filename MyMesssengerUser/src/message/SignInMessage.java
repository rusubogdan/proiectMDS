package message;

import graphicInterfaces.ChatWindow;

import com.entities.User;

public class SignInMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private String username;
	private String password;
	private ChatWindow chatWindow;

	public SignInMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void interactOnServer() {
		System.out.println("sunt pe server");
	}

	public void interactOnClient(ChatWindow chatWindow) {

	}
	
	public void interactOnClient() {

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

	public void setChatWindow(ChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}

}
