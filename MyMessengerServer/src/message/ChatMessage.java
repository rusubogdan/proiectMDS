package message;
import java.io.Serializable;
import java.util.List;

import com.entities.User;

public class ChatMessage extends Message implements Serializable{

	private static final long serialVersionUID = 1L;
    //din client catre server, apoi din server catre prietenii user-ului
	protected ChatMessage(User user, String message, List<User> friends) {
		super(user, message, friends);
	}

	
}
