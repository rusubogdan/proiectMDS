package message;

import graphicInterfaces.ChatWindow;

import java.io.Serializable;

import com.entities.User;

public interface Message extends Serializable{

	public User getUser();

	public void interactOnServer();

	public void interactOnClient(ChatWindow chatWindow);
}
