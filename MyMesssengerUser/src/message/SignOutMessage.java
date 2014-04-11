package message;

import java.io.Serializable;
import com.entities.User;

public class SignOutMessage extends Message implements Serializable {
	//ca si server vreau sa stiu carui user ii raspund printr-un mesaj de logOut la requestul de logOut
	//log out-ul poate fi doar cu succes; eventuala exceptie se trateaza in server
	//not sure if I need this class
	protected SignOutMessage(User user) {
		super(user, null, null);
	}

	private static final long serialVersionUID = 1L;

}