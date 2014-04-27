package message;

import graphicInterfaces.ChatWindow;

import java.util.ArrayList;

import com.entities.User;

public class ListOfFriendsMessage implements Message {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> friends = null;

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public User getUser() {
		return null;
	}

	public void interactOnServer() {

	}

	public void interactOnClient(ChatWindow chatWindow) {
		chatWindow.listOfUsersWindow.addUsersToList(friends);
	}

}
