package message;

import graphicInterfaces.AppHandler;
import graphicInterfaces.ListOfUsersWindow;

import java.util.ArrayList;

import com.entities.User;

public class ListOfFriendsMessage implements Message {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> friends;
	private ArrayList<String> friendsByName;
	private transient AppHandler appHandler;

	public ArrayList<String> getFriendsByName() {
		return friendsByName;
	}

	public void setFriendsByName(ArrayList<String> friendsByName) {
		this.friendsByName = friendsByName;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {
		System.out.println("Aici sunt in interactOnClient de pe ListOfFriendsMessage");

		ListOfUsersWindow.addUsersToList(friendsByName);

	}

	public AppHandler getAppHandler() {
		return appHandler;
	}

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

}
