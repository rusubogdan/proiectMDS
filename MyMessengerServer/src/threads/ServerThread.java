package threads;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.MessagesPanel;
import graphicInterfacesServer.MyPair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import message.ListOfFriendsMessage;
import message.Message;

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
	@SuppressWarnings("unused")
	private OnlineUsersThread onlineUsersThread;

	public static synchronized void addUserId(MyPair usernameId) {
		userIds.add(usernameId);
	}// ?????????????????????

	public ServerThread() {
		onlineUsersThread = new OnlineUsersThread();
		// odata cu instantierea se deschide si fereastra pentru useri online
		this.start();
		messageThread = new MessageThread();
	}

	public static synchronized void trigger(Connection connection) {
		System.out.println("in trigger din serverThread");
		sendFriendsToUsers(connection);
	}

	private static synchronized void sendFriendsToUsers(Connection conn) {
		ArrayList<User> auxList = new ArrayList<User>();
		//ArrayList<User> auxaux = new ArrayList<User>();
		//auxaux = onlineUsers;

		ListOfFriendsMessage message = new ListOfFriendsMessage();
		//message.setFriends(getOnlineUsers());

		for (Connection connection : connections) {
			if (connection.getUser() != null) {
				//auxList = message.getFriends();
				//boolean a = auxList.remove(conn.getUser());
				// elimin numele conexiunii la care trimit
				
				for(User user : onlineUsers)
					if(!connection.getUser().getUsername().equals(conn.getUser().getUsername()))
						auxList.add(user);
				
				System.out.println();
				for(User us : auxList)
					System.out.print(us.getUsername());
				System.out.println();
				
				message.setFriends(auxList);
				
				System.out.println(auxList.size());
				System.out.println(onlineUsers.size());

				MessageThread.addToQueueMess(message, null, connection);

				message.setFriends(onlineUsers);
				
				auxList.clear();
				//onlineUsers = auxaux;
				//System.out.println( "size:" + onlineUsers.size());
			}
		}
		
	//	message.setFriends(auxaux);

	}

	public static Connection getConnectionByUsername(String username) {

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

	public static boolean isTakken(String username) {
		if (userIds.size() == 0)
			return false;
		for (MyPair pair : userIds)
			if (pair.getUsername().equals(username))
				return true;
		return false;
	}

	public static String getUserFromUserIds(User user) {
		for (MyPair userAndId : userIds) {
			if (userAndId.getUsername().equals(user))
				return userAndId.getUsername();
		}
		return null;
	}

	public static long getIdFromUserIds(long userId) {
		for (MyPair userAndId : userIds) {
			if (userAndId.getUserId() == userId)
				return userAndId.getUserId();
		}
		return 0;
	}

	public synchronized static void removeConnection(Connection connection) {
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

	public void cancel() {
		for (Connection connection : connections) {
			connection.cancel();
		}
	}

	public static synchronized void close() {
		isClosed = true;

	}

	public void run() {
		int serverPort = 9999;
		Socket clientSocket;
		Connection connection;

		try {
			serverSocket = new ServerSocket(serverPort);
			System.out.println("Server is ready!");
			// serverSocket.setSoTimeout(10000);

			while (!isClosed) {
				clientSocket = serverSocket.accept();
				connection = new Connection(clientSocket, MessagesPanel.textField);
				connections.add(connection);
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
		OnlineUsersThread.alter();
	}

	public synchronized static void removeFromOnlineUsersQueue(User user) {
		System.out.println("din remove in server thread!!!");
		onlineUsers.remove(user);
		OnlineUsersThread.alter();
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
