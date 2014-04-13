package message;

import com.entities.User;

public class SignUpUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user;

	public SignUpUnsuccesfullMessage() {
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

}
