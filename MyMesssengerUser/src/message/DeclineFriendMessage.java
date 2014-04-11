package message;

import java.util.List;

import com.entities.User;

public class DeclineFriendMessage extends Message {

	private static final long serialVersionUID = 1L;

	public DeclineFriendMessage(User theUserWhoRequestedFriendship,
			List<User> theUserWhoDeclined) {
		super(theUserWhoRequestedFriendship, null, theUserWhoDeclined);
	}

}
