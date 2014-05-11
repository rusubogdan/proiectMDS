package message;

import graphicInterfaces.AppHandler;

public class SignInUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private AppHandler appHandler;

	public SignInUnsuccesfullMessage() {
	}

	public void interactOnClient() {
		appHandler.signInUnsuccesfully();
	}

	public AppHandler getAppHandler() {
		return appHandler;
	}

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

}
