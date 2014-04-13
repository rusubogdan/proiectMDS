package graphicInterfacesServer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.entities.User;

import message.Message;

public class MessagesPanel {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static boolean isClosed = false;
	private static ArrayList<Connection> connections = new ArrayList<>();
	private static ArrayList<MyPair> userIds = new ArrayList<MyPair>();

	public static void addUserId(MyPair usernameId) {
		userIds.add(usernameId);
	}

	public synchronized static void addMessageToMessageSender(Message message,
			Connection connectionOfSender, Connection connectionOfReceiver) {
		connectionOfReceiver.addToQueueConnection(message);

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

	// //////////////////////////////////////////
	public synchronized static void addMessageToMessageSender(Message message) {
		for (Connection connection : connections) {
			connection.addToQueueConnection(message);
		}
	}

	// ////////////////////////////////
	public synchronized static void removeConnection(Connection connection) {
		connections.remove(connection);

	}

	public void cancel() {
		for (Connection connection : connections) {
			connection.cancel();
		}
	}

	public static synchronized void close() {
		isClosed = true;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessagesPanel window = new MessagesPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MessagesPanel() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 121, 287, 130);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton buttonStartServer = new JButton("StartServer");
		buttonStartServer.addActionListener(new ActionListener() {
			private ServerSocket serverSocket;

			public void actionPerformed(ActionEvent e) {
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
							connection = new Connection(clientSocket, textField_1);
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
		});
		buttonStartServer.setBounds(321, 175, 89, 23);
		frame.getContentPane().add(buttonStartServer);

		textField_1 = new JTextField();
		textField_1.setBounds(10, 27, 287, 47);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}

}
