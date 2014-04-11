package graphicInterfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import message.Message;

public class ChatWindow {

	private JFrame frame;
	private JTextField messageField;
	private JTextField textField;
	private String messageToSend;
	private String serverAddress = "localhost";
	private int serverPort = 9999;
	private Socket socket;
	private Message message;
	private ObjectOutputStream objectOutputStream;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatWindow window = new ChatWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		messageField = new JTextField();
		messageField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageToSend = messageField.getText();
				messageField.setText("");
				message = new Message(null, messageToSend, null);
				try {
					objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(message);
					objectOutputStream.flush();
				} catch (Exception ewew) {

				}
			}
		});
		messageField.setBounds(10, 172, 312, 79);
		frame.getContentPane().add(messageField);
		messageField.setColumns(10);

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageToSend = messageField.getText();
				messageField.setText("");
				message = new Message(null, messageToSend, null);
				try {
					objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(message);
					objectOutputStream.flush();
				} catch (Exception ewew) {

				}
			}
		});
		sendButton.setBounds(332, 181, 89, 52);
		frame.getContentPane().add(sendButton);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					socket = new Socket(serverAddress, serverPort);
					System.out.println("Connection Success!");

				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
		});
		btnConnect.setBounds(332, 75, 89, 23);
		frame.getContentPane().add(btnConnect);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 11, 312, 150);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
