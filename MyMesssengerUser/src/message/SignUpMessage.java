package message;

import graphicInterfaces.AppHandler;

public class SignUpMessage implements Message {

	private static final long serialVersionUID = 1L;
	private AppHandler appHandler;
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public SignUpMessage(String name, String password) {
		this.name = name;
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
