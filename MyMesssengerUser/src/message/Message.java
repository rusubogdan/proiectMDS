package message;

import graphicInterfaces.AppHandler;

import java.io.Serializable;

public interface Message extends Serializable{

	public void interactOnClient();

	public void setAppHandler(AppHandler appHandler);

}
