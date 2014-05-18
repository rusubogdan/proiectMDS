package message;

public class AddFriendMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String friend;
	
	public void setUser(String user) {
		this.user = user;
	}

	public void setFriend(String friend) {
		this.friend = friend;
	}

	public AddFriendMessage(String user, String friends) {
		this.user = user;
		this.friend = friends;
	}

	public String getUser() {
		return user;
	}

	public String getFriend() {
		return friend;
	}

}
