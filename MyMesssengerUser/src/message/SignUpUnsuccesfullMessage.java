package message;

import graphicInterfaces.AppHandler;

public class SignUpUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private AppHandler appHandler;

	public SignUpUnsuccesfullMessage() {
	}

	public void interactOnClient() {
		appHandler.signUpUnsuccesfully();
	}

	public AppHandler getAppHandler() {
		return appHandler;
	}

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

}
