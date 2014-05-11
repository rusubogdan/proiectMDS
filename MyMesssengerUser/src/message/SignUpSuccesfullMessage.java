package message;

import graphicInterfaces.AppHandler;

public class SignUpSuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private AppHandler appHandler;

	public SignUpSuccesfullMessage() {
	}

	public void interactOnClient() {
		appHandler.signUpSuccesfully();
	}

	public AppHandler getAppHandler() {
		return appHandler;
	}

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

}
