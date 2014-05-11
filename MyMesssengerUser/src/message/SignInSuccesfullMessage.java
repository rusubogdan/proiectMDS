package message;

import graphicInterfaces.AppHandler;

public class SignInSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private AppHandler appHandler;

	public SignInSuccesfullMessage() {
	}

	public void interactOnClient() {
		appHandler.signInSuccesfully();
	}

	public AppHandler getAppHandler() {
		return appHandler;
	}

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

}
