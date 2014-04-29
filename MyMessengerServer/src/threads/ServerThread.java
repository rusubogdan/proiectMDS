package threads;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.StartServerWindow;
import graphicInterfacesServer.MyPair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import message.ListOfFriendsMessage;
import message.Message;
import message.ServerHasBeenClosedMessage;

import com.entities.User;

/*	Ar trebui ca de fiecare data cand pornesc threadul ServerThread sa iau 
 * 	din baza de date o lista de perechi cu username si id 
 *  De asemenea sa actualizez de fiecare data o lista cu userii online
 * 
 */
public class ServerThread extends Thread {

	private static boolean isClosed = false;
	private static ArrayList<Connection> connections = new ArrayList<>();
	private static ArrayList<MyPair> userIds = new ArrayList<MyPair>();
	private static ArrayList<User> onlineUsers = new ArrayList<User>();
	private ServerSocket serverSocket;
	private MessageThread messageThread;
	private OnlineUsersThread onlineUsersThread;

	public static synchronized void addUserId(MyPair usernameId) {
		userIds.add(usernameId);
	}// ?????????????????????

	public ServerThread() {
		isClosed = false;
		if (onlineUsersThread == null)
			onlineUsersThread = new OnlineUsersThread(this);
		// odata cu instantierea se deschide si fereastra pentru useri online
		if (messageThread == null)
			messageThread = new MessageThread();
		this.start();
	}

	public static synchronized void trigger() {
		System.out.println("In trigger din serverThread");

		sendFriendsToUsers();
	}

	public static synchronized ArrayList<Connection> getConnections() {
		return connections;
	}

	private static synchronized void sendFriendsToUsers() {
		ListOfFriendsMessage message = null;
		ArrayList<User> list = null;
		Connection connection = null;

		ArrayList<User> LIST = null;
		LIST = new ArrayList<>(getOnlineUsers());
		System.out.println("                                 DA a ajuns");
		for (User user : LIST) {
			list = new ArrayList<>(getOnlineUsers());
			connection = getConnectionByUsername(user.getUsername());

			message = new ListOfFriendsMessage();
			list.remove(user);
			message.setFriends(list);

			message.setConnectionOfReceiver(connection);

			MessageThread.addToQueueMess(message);

			message = null;
		}

	}

	public synchronized static Connection getConnectionByUsername(String username) {

		for (Connection connection : connections) {
			if (connection.getUser().getUsername().equals(username)) {
				return connection;
			}
		}
		return null;
	}

	public synchronized static void addMessageToMessageSender(Message message) {
		message.getConnectionOfSender().addToQueueConnection(message);
	}

	public synchronized static boolean isTakken(String username) {
		if (userIds.size() == 0)
			return false;
		for (MyPair pair : userIds)
			if (pair.getUsername().equals(username))
				return true;
		return false;
	}

	public static synchronized String getUserFromUserIds(User user) {
		for (MyPair userAndId : userIds) {
			if (userAndId.getUsername().equals(user))
				return userAndId.getUsername();
		}
		return null;
	}

	public synchronized static long getIdFromUserIds(long userId) {
		for (MyPair userAndId : userIds) {
			if (userAndId.getUserId() == userId)
				return userAndId.getUserId();
		}
		return 0;
	}

	public synchronized static void removeConnection(Connection connection) {
		if (connection == null) {
			for (Connection connection2 : connections) {
				connection2.cancel();
			}
		} else
			connections.remove(connection);

	}

	public synchronized static void removeConnectionByUsername(String username) {
		for (Connection connection : connections) {
			if (connection.getUser().getUsername().equals(username)) {
				connections.remove(connection);
				break;
			}
		}
	}

	private static synchronized void cancel() {
		for (Connection connection : connections) {
			connection.cancel();
		}
	}

	public static synchronized void close() {
		isClosed = true;
		ServerHasBeenClosedMessage message = new ServerHasBeenClosedMessage();
		message.setConnectionOfReceiver(null);
		message.setConnectionOfSender(null);
		addMessageToMessageSender(message);
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cancel();

	}

	public static synchronized void addConnection(Connection connection) {
		connections.add(connection);
	}

	public void run() {
		int serverPort = 9999;
		Socket clientSocket;
		Connection connection;

		try {
			if (serverSocket == null)
				serverSocket = new ServerSocket(serverPort);
			System.out.println("Server is ready!");

			while (!isClosed) {
				clientSocket = serverSocket.accept();
				connection = new Connection(clientSocket, StartServerWindow.textField);
				ServerThread.addConnection(connection);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		messageThread.cancel();

	}

	public synchronized static void addToOnlineUsersQueue(User user) {
		System.out.println("add to online users  server thread");
		onlineUsers.add(user);
		OnlineUsersThread.alter();// modifica fereastra cu userii online
		trigger();
	}

	public synchronized static void removeFromOnlineUsersQueue(User user) {
		System.out.println("din remove in server thread!!!");
		onlineUsers.remove(user);
		OnlineUsersThread.alter();// modifica fereastra cu userii online
		trigger();
	}

	public synchronized static ArrayList<User> getOnlineUsers() {
		return onlineUsers;
	}

	public synchronized static boolean isOnline(String username) {
		for (User user : onlineUsers) {
			if (user.getUsername().equals(username)) {
				return true;
			}

		}
		return false;
	}
}
