package message;

public class ChatMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String message;
	private String friend;

	public ChatMessage(String user, String message, String friend) {
		this.user = user;
		this.message = message;
		this.friend = friend;
	}

	public String getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}