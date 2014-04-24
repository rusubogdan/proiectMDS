package threads;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.MessagesPanel;
import graphicInterfacesServer.MyPair;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.entities.User;
import message.Message;

/*	Ar trebui ca de fiecare data cand pornesc threadul ServerThread sa iau 
 * 	din baza de date o lista de perechi cu username si id 
 *  De asemenea sa actualizez de fiecare data o lista cu userii online
 * 
 */
public class ServerThread extends Thread {

	private static boolean isClosed = false;
	private static ArrayList<Connection> connections = new ArrayList<>();
	private static ArrayList<MyPair> userIds = new ArrayList<MyPair>();
	private ServerSocket serverSocket;
	private MessageThread messageThread;

	public static synchronized void addUserId(MyPair usernameId) {
		userIds.add(usernameId);
	}// ?????????????????????

	public ServerThread() {
		this.start();
		messageThread = new MessageThread();
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
			try {
				serverSocket = new ServerSocket(serverPort);
				System.out.println("Server is ready!");
				// serverSocket.setSoTimeout(10000);

				while (!isClosed) {
					clientSocket = serverSocket.accept();
					connection = new Connection(clientSocket, MessagesPanel.textField);
					connections.add(connection);
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			} catch (IllegalArgumentException iae) {
				iae.printStackTrace();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
}
