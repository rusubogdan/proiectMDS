package message;

import com.entities.User;

public class SignInUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;

	public SignInUnsuccesfullMessage() {
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

}
