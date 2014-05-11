package message;

import java.util.ArrayList;
import graphicInterfacesServer.Connection;
import com.entities.User;

public class ListOfFriendsMessage implements Message {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<User> friends = null;
	private ArrayList<String> friendsByName = null;
	
	private transient Connection connectionOfSender;
	private transient Connection connectionOfReceiver;

	public ArrayList<User> getFriends() {
		return friends;
	}

	public ArrayList<String> getFriendsByName() {
		return friendsByName;
	}

	public void setFriendsByName(ArrayList<String> friendsByName) {
		this.friendsByName = friendsByName;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}


	public void interactOnServer(Connection connection, Connection connection2) {

	}

	public Connection getConnectionOfSender() {
		return connectionOfSender;
	}

	public Connection getConnectionOfReceiver() {
		return connectionOfReceiver;
	}

	public void setConnectionOfSender(Connection connection) {
		connectionOfSender = connection;
	}

	public void setConnectionOfReceiver(Connection connection) {
		connectionOfReceiver = connection;
	}

}
