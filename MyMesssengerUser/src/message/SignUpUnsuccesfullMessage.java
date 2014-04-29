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

	public void interactOnClient(ChatWindow chatWindow) {
		System.out.println("unsuccesfull on client");
		chatWindow.clearFields();
	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
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
