package message;

import java.io.Serializable;
import java.util.List;
import com.entities.User;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Integer messageId;
	protected User user;
	public String message;
	protected List<User> friends;

	public User getUser() {
		return user;
	}

	public Message(User user, String message, List<User> friends) {
		this.user = user;
		this.message = message;
		this.friends = friends;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
