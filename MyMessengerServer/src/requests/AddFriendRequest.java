package requests;

import com.entities.User;
import message.Message;

public class AddFriendRequest extends Message {

	private static final long serialVersionUID = 1L;

	// de la user catre server un request; apoi de la server catre friendName un
	// request;
	// de la frind la server un mesaj de accept sau respingere; serverul adauga
	// sau nu; apoi trimite catre client un mesaj
	//constructor Request : username, password, passwordAgain
	public AddFriendRequest(User user, String friendName) {
		super(user, friendName, null);
	}

}
