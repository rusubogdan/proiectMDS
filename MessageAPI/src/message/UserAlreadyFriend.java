package message;

public class UserAlreadyFriend implements Message {

	private static final long serialVersionUID = 1L;

	private String user;
	private String friend;

	public UserAlreadyFriend(String user, String friend) {
		this.user = user;
		this.friend = friend;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFriend() {
		return friend;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

}
