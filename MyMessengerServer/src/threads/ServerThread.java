package threads;

import graphicInterfacesServer.Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.Set;

import message.ServerHasBeenClosedMessage;

public class ServerThread extends Thread {

	private static boolean isClosed = false;
	private static Set<Connection> connections = new HashSet<>();
	private static Set<String> onlineUsers = new HashSet<>();

	private ServerSocket serverSocket;
	private OnlineUsersThread onlineUsersThread;
	private int serverPort = 9999;

	public static synchronized boolean userOnline(String username) {

		for (String user : getOnlineUsers()) {
			if (user.equals(username)) {
				return true;
			}
		}
		return false;
	}

	public ServerThread() {
		isClosed = false;
		if (onlineUsersThread == null)
			onlineUsersThread = new OnlineUsersThread(this);
		this.start();
	}

	public synchronized static Connection getConnectionByUsername(String username) {

		for (Connection connection : connections) {
			if (connection.getUsername().equals(username)) {
				return connection;
			}
		}
		return null;
	}

	public synchronized static void removeConnection(Connection connection) {
		if (connection == null) {
			for (Connection connection2 : connections) {
				connection2.cancel();
			}

			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else
			connections.remove(connection);

	}

	public synchronized static void removeConnectionByUsername(String username) {
		for (Connection connection : connections) {
			if (connection.getUsername().equals(username)) {
				connections.remove(connection);
				break;
			}
		}
	}

	private static synchronized void cancelAllTheConnections() {
		for (Connection connection : connections) {
			connection.cancel();
		}
	}

	/*
	 * Trimite un mesaj specific userilor: ServerHasBennClosedMessage
	 */
	public static synchronized void alertUsers() {
		ServerHasBeenClosedMessage message = new ServerHasBeenClosedMessage();

		for (Connection connection : connections) {
			connection.addToQueueConnection(message);
		}
	}

	public static synchronized void closeServerThread() {
		cancelAllTheConnections();

		isClosed = true;

	}

	public static synchronized void addConnection(Connection connection) {
		connections.add(connection);
	}

	public void run() {

		try {
			if (serverSocket == null) {
				serverSocket = new ServerSocket(serverPort);
				serverSocket.setSoTimeout(5000);
			}
			Connection connection;
			Socket clientSocket;

			while (!isClosed) {
				try {
					clientSocket = serverSocket.accept();
					connection = new Connection(clientSocket);
					ServerThread.addConnection(connection);
					connection.start();
				} catch (SocketTimeoutException ste) {/* secunde. nothing happened */
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized static void addToOnlineUsers(String username) {
		onlineUsers.add(username);
		OnlineUsersThread.alter();// modifica fereastra cu userii online
	}

	public synchronized static void removeFromOnlineUsers(String username) {
		System.out.println("Am eliminat din userii online: " + username);
		onlineUsers.remove(username);
		OnlineUsersThread.alter();// modifica fereastra cu userii online
	}

	public synchronized static Set<String> getOnlineUsers() {
		return onlineUsers;
	}

}
