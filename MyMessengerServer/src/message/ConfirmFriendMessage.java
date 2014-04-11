package message;

import java.util.List;

import com.entities.User;

public class ConfirmFriendMessage extends Message {

	private static final long serialVersionUID = 1L;

	public ConfirmFriendMessage(User theUserWhoRequestedFriendship,
			List<User> theUserWhoAccepted) {
		super(theUserWhoRequestedFriendship, null, theUserWhoAccepted);
	}

}
