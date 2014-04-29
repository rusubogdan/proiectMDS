package message;

import graphicInterfaces.ChatWindow;
import graphicInterfaces.ListOfUsersWindow;

import java.util.ArrayList;

import com.entities.User;

public class ListOfFriendsMessage implements Message {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> friends = null;
	private ChatWindow chatWindow = null;
	private ArrayList<String> friendsByName = null;

	public ArrayList<String> getFriendsByName() {
		return friendsByName;
	}

	public void setFriendsByName(ArrayList<String> friendsByName) {
		this.friendsByName = friendsByName;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public ListOfFriendsMessage(ChatWindow chatWindow) {
		this.chatWindow = chatWindow;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public User getUser() {
		return null;
	}

	public void interactOnServer() {

	}

	public void interactOnClient() {
		System.out.println("Aici sunt in interactOnClient de pe ListOfFriendsMessage");

		ListOfUsersWindow.addUsersToList(friendsByName);
		
	}

	public void interactOnClient(ChatWindow chatWindow) {
		ListOfUsersWindow.addUsersToList(friendsByName);
	}

	public void setChatWindow(ChatWindow chatWindow) {
		this.chatWindow = chatWindow;
		
	}

	public ChatWindow getChatWindow() {
		return chatWindow;
	}

}
