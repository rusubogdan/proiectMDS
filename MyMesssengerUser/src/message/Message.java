package message;

import graphicInterfaces.ChatWindow;

import java.io.Serializable;

public interface Message extends Serializable{

	public void interactOnClient();
	
	public void setChatWindow(ChatWindow chatWindow);
	
	public ChatWindow getChatWindow() ;
}
