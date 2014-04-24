package graphicInterfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import threads.MessageReceiver;
import message.ChatMessage;
import message.Message;
import message.SignInMessage;
import message.SignUpMessage;

import com.entities.User;

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
	private JButton btnSignIn_1;
	private static JTextField passwordTF;
	private static JTextField confirmPasswordTF;
	private static JTextField usernameTF;

	public static void clearFields() {
		usernameTF.setText("");
		passwordTF.setText("");
		confirmPasswordTF.setText("");
	}

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
				User user = new User();
				ArrayList<User> list = new ArrayList<User>();

				message = new ChatMessage(user, messageToSend, list);
				try {
					objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(message);
					objectOutputStream.flush();
				} catch (Exception ewew) {
					ewew.printStackTrace();
				}
			}
		});
		messageField.setBounds(10, 172, 201, 79);
		frame.getContentPane().add(messageField);
		messageField.setColumns(10);

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messageToSend = messageField.getText();
				messageField.setText("");
				User user = new User();
				ArrayList<User> list = new ArrayList<User>();
				message = new ChatMessage(user, messageToSend, list);
				try {
					objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(message);
					objectOutputStream.flush();
				} catch (Exception ewew) {
					ewew.printStackTrace();
				}
			}
		});
		sendButton.setBounds(235, 218, 101, 33);
		frame.getContentPane().add(sendButton);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			private MessageReceiver messageReceiver = null;

			public void actionPerformed(ActionEvent e) {

				try {
					socket = new Socket(serverAddress, serverPort);
					System.out.println("Connection Success!");
					messageReceiver = new MessageReceiver(socket);

				} catch (IOException ee) {
					ee.printStackTrace();
				}
			}
		});
		btnConnect.setBounds(332, 11, 89, 23);
		frame.getContentPane().add(btnConnect);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(10, 11, 201, 150);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		usernameTF = new JTextField();
		usernameTF.setBounds(255, 76, 154, 20);
		frame.getContentPane().add(usernameTF);
		usernameTF.setColumns(10);

		passwordTF = new JTextField();
		passwordTF.setBounds(255, 107, 154, 20);
		frame.getContentPane().add(passwordTF);
		passwordTF.setColumns(10);

		confirmPasswordTF = new JTextField();
		confirmPasswordTF.setBounds(255, 138, 154, 20);
		frame.getContentPane().add(confirmPasswordTF);
		confirmPasswordTF.setColumns(10);

		JButton btnSignIn = new JButton("Sign Up");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = new String(), password = new String(), passwordConfirm = new String();

				username = usernameTF.getText();
				password = passwordTF.getText();
				passwordConfirm = confirmPasswordTF.getText();

				if (username.equals(""))
					System.out.println("Username field must not be empty!");
				else {
					if (password.equals("") || passwordConfirm.equals("")
							|| !password.equals(passwordConfirm))
						System.out
								.println("Password field is empty or not well confirmed!");
					else {
						Message signUpMessage = new SignUpMessage(username, password);

						try {
							objectOutputStream = new ObjectOutputStream(socket
									.getOutputStream());
							objectOutputStream.writeObject(signUpMessage);
							objectOutputStream.flush();

						} catch (Exception ewew) {
							System.out.println("ups");
						}
					}
				}
			}
		});
		btnSignIn.setBounds(332, 172, 89, 23);
		frame.getContentPane().add(btnSignIn);

		btnSignIn_1 = new JButton("Sign In");
		btnSignIn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = new String(), password = new String();
				username = usernameTF.getText();
				password = passwordTF.getText();

				if (username.equals(""))
					System.out.println("Username field must not be empty!");
				else {
					if (password.equals(""))
						System.out.println("Password field is empty !");
					else {
						Message signUpMessage = new SignInMessage(username, password);

						try {
							objectOutputStream = new ObjectOutputStream(socket
									.getOutputStream());
							objectOutputStream.writeObject(signUpMessage);
							objectOutputStream.flush();

						} catch (Exception ewew) {
							System.out.println("ups");
						}
					}
				}

			}
		});
		btnSignIn_1.setBounds(235, 172, 89, 23);
		frame.getContentPane().add(btnSignIn_1);
	}

	public static void openNewWindow() {
		ListOfUsersWindow.openWindow();
	}

	public static void openWarningWindow() {
		WarningWindow.openWindow();
		
	}
}
