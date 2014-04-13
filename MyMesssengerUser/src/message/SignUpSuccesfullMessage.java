package message;

import com.entities.User;

public class SignUpSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;

	public SignUpSuccesfullMessage() {
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

}
