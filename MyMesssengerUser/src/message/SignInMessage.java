package message;

import graphicInterfaces.AppHandler;


public class SignInMessage implements Message {

	private static final long serialVersionUID = 1L;
	private AppHandler appHandler;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


	public SignInMessage(String name, String password) {
		username = name;
		this.password = password;
	}

	public void interactOnClient() {
	}

	public AppHandler getAppHandler() {
		return appHandler;
	}

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}


}
