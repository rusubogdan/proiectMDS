package message;

import java.util.List;

public class ListOfFriendsMessage implements Message {

	private static final long serialVersionUID = 1L;
	private List<String> friendsByName;
	
	public List<String> getFriendsByName() {
		return friendsByName;
	}
	public void setFriendsByName(List<String> friendsByName) {
		this.friendsByName = friendsByName;
	}
	
}
