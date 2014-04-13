package message;

import java.util.List;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.ManageUsers;

import com.entities.User;

public class SignInMessage implements Message {

	private static final long serialVersionUID = 1L;
	private User user = null;
	private String username;
	private String password;
	private Connection connection;

	public SignInMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void interactOnServer() {
		System.out.println("sunt pe server");
		ManageUsers manager = new ManageUsers();
		List list = null;

		list = manager.getListOfUsers();
		if (list == null) {

		}
	}

	public void interactOnClient() {

	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}
