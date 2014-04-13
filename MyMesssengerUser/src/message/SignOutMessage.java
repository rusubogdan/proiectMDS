package message;

import com.entities.User;

public class SignOutMessage implements Message {
	// ca si server vreau sa stiu pe cine deconectez
	// sign out-ul poate fi doar cu succes; eventuala exceptie se trateaza in
	// server

	private static final long serialVersionUID = 1L;
	private User user;

	public SignOutMessage(User user) {
		this.user = user;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

}