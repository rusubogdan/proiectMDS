package graphicInterfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import message.Message;
import message.SignInMessage;
import message.SignUpMessage;
import threads.MessageReceiver;

import com.entities.User;

public class ChatWindow {

	private JFrame frame;
	private String serverAddress = "localhost";
	private int serverPort = 9999;
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private JButton btnSignIn;
	private JTextField passwordTF;
	private JTextField confirmPasswordTF;
	private JTextField usernameTF;
	private MessageReceiver messageReceiver = null;
	public String name;
	private boolean isConnectedToServer = false;
	public ListOfUsersWindow listOfUsersWindow;

	public synchronized void closeWindow() {
		frame.dispose();
	}

	public synchronized void showWarningPanel() {

	}

	public void clearFields() {
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

	public synchronized void openConversation(String friend, ChatWindow chatWindow) {
		User user = new User();
		@SuppressWarnings("unused")
		ArrayList<User> friends = new ArrayList<User>();
		user.setUsername(name);

	}

	private synchronized void connectToServer() {
		try {

			socket = new Socket(serverAddress, serverPort);
			System.out.println("Connection Success!");
			messageReceiver = new MessageReceiver(socket, this);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			isConnectedToServer = true;

		} catch (IOException ee) {
			ee.printStackTrace();
		}
	}
	
	public synchronized void disconnectFromServer() {
		try{
			socket.close();
			messageReceiver.cancel();
			objectOutputStream.close();
			isConnectedToServer = false;
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void instantiate() {
		listOfUsersWindow = new ListOfUsersWindow(this);
	}

	public ChatWindow() {
		initialize();
	}


	private synchronized void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 257, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		usernameTF = new JTextField();
		usernameTF.setBounds(31, 110, 154, 20);
		frame.getContentPane().add(usernameTF);
		usernameTF.setColumns(10);

		passwordTF = new JTextField();
		passwordTF.setBounds(31, 157, 154, 20);
		frame.getContentPane().add(passwordTF);
		passwordTF.setColumns(10);

		confirmPasswordTF = new JTextField();
		confirmPasswordTF.setBounds(31, 204, 154, 20);
		frame.getContentPane().add(confirmPasswordTF);
		confirmPasswordTF.setColumns(10);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!isConnectedToServer)
					connectToServer();

				String username = new String(), password = new String(), passwordConfirm = new String();

				username = usernameTF.getText();
				password = passwordTF.getText();
				passwordConfirm = confirmPasswordTF.getText();

				if (username.equals("")) {
					WarningWindow.openWarningWindow("Username field is empty!");
				} else {
					if (password.equals("") || passwordConfirm.equals("")
							|| !password.equals(passwordConfirm)) {
						WarningWindow.openWarningWindow("Password field is empty!");
					} else {
						Message signUpMessage = new SignUpMessage(username, password);

						try {
							objectOutputStream.writeObject(signUpMessage);
							objectOutputStream.flush();

						} catch (IOException ioe) {
							System.out.println("IOException in chat window");
							ioe.printStackTrace();
						} catch (NullPointerException npe) {
							System.out.println("null pointer exception in chat window");
							System.out.println("NU E CONECTAT");
						} finally {
							clearFields();
						}
					}
				}

			}
		});
		btnSignUp.setBounds(57, 270, 98, 23);
		frame.getContentPane().add(btnSignUp);

		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!isConnectedToServer)
					connectToServer();

				String username = new String(), password = new String();
				username = usernameTF.getText();
				password = passwordTF.getText();

				if (username.equals("")) {
					WarningWindow.openWarningWindow("Username field is empty!");
				} else {
					if (password.equals("")) {
						WarningWindow.openWarningWindow("Password field is empty!");
					} else {

						Message signInMessage = new SignInMessage(username, password);

						try {
							name = username;
							System.out.println(name);
							objectOutputStream.writeObject(signInMessage);
							objectOutputStream.flush();

						} catch (IOException ioe) {
							System.out.println("IOException in chat window");
							ioe.printStackTrace();
						} catch (NullPointerException npe) {
							System.out.println("null pointer exception in chat window");
							System.out.println("NU E CONECTAT");
						} finally {
							clearFields();
						}
					}
				}

			}
		});
		btnSignIn.setBounds(57, 235, 98, 23);
		frame.getContentPane().add(btnSignIn);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(31, 92, 98, 14);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(31, 141, 68, 14);
		frame.getContentPane().add(lblPassword);

		JLabel lblPasswordAgain = new JLabel("Password again:");
		lblPasswordAgain.setBounds(31, 188, 98, 14);
		frame.getContentPane().add(lblPasswordAgain);
	}

	public void sendMessageToServer(Message message) {
		try {
			System.out.println("Am trimis mesajul");
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
		} catch (IOException e) {
			System.out.println("I/O Exception in chatWindow's sender");
			e.printStackTrace();
		} 
		
	}

}
