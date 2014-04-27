package message;

import java.util.ArrayList;

import graphicInterfacesServer.Connection;

import com.entities.User;

public class ListOfFriendsMessage implements Message{

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

	public void interactOnServer(Connection connection, Connection connection2) {
		
	}

	
	
	public void interactOnClient() {
		
	}

	public Connection getConnectionOfSender() {
		return null;
	}

	public Connection getConnectionOfReceiver() {
		return null;
	}

	public void setConnectionOfSender(Connection connection) {
		
	}

	public void setConnectionOfReceiver(Connection connection) {
		
	}

}
