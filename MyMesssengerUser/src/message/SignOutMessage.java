package message;

import graphicInterfaces.AppHandler;

public class SignOutMessage implements Message {
	// ca si server vreau sa stiu pe cine deconectez
	// sign out-ul poate fi doar cu succes; eventuala exceptie se trateaza in
	// server

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String username;

	public SignOutMessage(String name) {
		this.username = name;
	}

	public void interactOnClient() {

	}

	public void setAppHandler(AppHandler appHandler) {

	}

}