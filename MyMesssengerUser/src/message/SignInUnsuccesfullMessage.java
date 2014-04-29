package message;

import graphicInterfaces.ChatWindow;
import graphicInterfaces.WarningWindow;

import com.entities.User;

public class SignInUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private ChatWindow chatWindow = null;

	public SignInUnsuccesfullMessage() {
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {
		WarningWindow.openWarningWindow("Incorrect username or password!");
	}

	public void interactOnClient(ChatWindow chatWindow) {
		//chatWindow.openWarningWindow();
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
