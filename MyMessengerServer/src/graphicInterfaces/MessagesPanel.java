package graphicInterfaces;

import graphicInterfaces.Connection;
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
import message.Message;

public class MessagesPanel {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static boolean isClosed = false;
	private static boolean isCanceled = false;
	private static ArrayList<Connection> connections = new ArrayList<>();

	public synchronized static void addMessageToMessageSender(Message message) {
		for (Connection connection : connections) {
			Connection.addToQueueConnection(message);
		}
	}

	public void cancel() {
		for (Connection connection : connections) {
			Connection.cancel();
		}
		isCanceled = true;
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
				try {
					try {
						serverSocket = new ServerSocket(serverPort);
						System.out.println("Server is ready!");
						serverSocket.setSoTimeout(10000);

						while (!isClosed) {
							clientSocket = serverSocket.accept();
							new Connection(clientSocket, textField_1);

						}
					} catch (IOException exception) {
						exception.printStackTrace();
					} catch (IllegalArgumentException iae) {
						iae.printStackTrace();
					}

				} catch (Exception e1) {

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
