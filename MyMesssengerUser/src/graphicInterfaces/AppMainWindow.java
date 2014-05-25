package graphicInterfaces;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import message.Message;
import message.SignInMessage;
import message.SignUpMessage;

public class AppMainWindow {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmedPasswordField;
	private String username;
	private ListOfUsersWindow listOfUsersWindow;
	private AppHandler appHandler;

	public ListOfUsersWindow getListOfUsersWindow() {
		return listOfUsersWindow;
	}
	
	public String getUsername() {
		return username;
	}
	
	public synchronized void closeWindow() {
		frame.dispose();
	}

	public void clearFields() {
		usernameField.setText("");
		passwordField.setText("");
		confirmedPasswordField.setText("");
	}

	public AppMainWindow() {
		initialize();
	}

	public void setAppHandler(AppHandler handler) {
		appHandler = handler;
	}

	private synchronized void initialize() {
		frame = new JFrame();

		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 257, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		usernameField = new JTextField();
		usernameField.setBounds(31, 110, 154, 20);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(31, 157, 154, 20);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);

		confirmedPasswordField = new JPasswordField();
		confirmedPasswordField.setBounds(31, 204, 154, 20);
		frame.getContentPane().add(confirmedPasswordField);
		confirmedPasswordField.setColumns(10);

		frame.setVisible(true);

		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					
					String username = new String();
					char pass[] = new char[30];
					
					username = usernameField.getText();

					pass = passwordField.getPassword();

					String password = new String(pass);
					
					if (username.equals("")) {
						new WarningWindows("Username field is empty!");
					} else {
						if (password.equals("")) {
							new WarningWindows("Password field is empty!");
						} else {

							appHandler.connectToServer();

							Message signInMessage = new SignInMessage(username, password);
							appHandler.setNameOfUser(username);

							appHandler.addMessageToQueue(signInMessage);

						}
					}
				}
			}
		});

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String username = new String();

				username = usernameField.getText();
				
				char pass[] = new char[30];
				pass = passwordField.getPassword();

				char passConfirmed[] = new char[30];
				passConfirmed = confirmedPasswordField.getPassword();

				String password = new String(pass);
				String passwordConfirm = new String(passConfirmed);
				
				if (username.equals("")) {
					new WarningWindows("Username field is empty!");
				} else {
					if (password.equals("") || passwordConfirm.equals("")
							|| !password.equals(passwordConfirm)) {
						new WarningWindows("Password fields are empty or different!");
					} else {

						appHandler.connectToServer();

						Message signUpMessage = new SignUpMessage(username, password);
						appHandler.addMessageToQueue(signUpMessage);

					}
				}

			}
		});
		btnSignUp.setBounds(57, 270, 98, 23);
		frame.getContentPane().add(btnSignUp);

		
		
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = new String();
				username = usernameField.getText();
				
				char pass[] = new char[30];
				pass = passwordField.getPassword();

				String password = new String(pass);

				if (username.equals("")) {
					new WarningWindows("Username field is empty!");
				} else {
					if (password.equals("")) {
						new WarningWindows("Password field is empty!");
					} else {

						appHandler.connectToServer();

						Message signInMessage = new SignInMessage(username, password);

						appHandler.setNameOfUser(username);

						appHandler.addMessageToQueue(signInMessage);

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

	public void signUpSuccesfully() {
		new WarningWindows("SignUp succesfully!");
	}

	public void signInUnsuccesfully() {
		new WarningWindows("User and password do not match!");
	}

	public void signUpUnsuccesfully() {
		new WarningWindows("User already exists!");
	}

	public void addFriendSuccesfully() {
		new WarningWindows("Friend added!");
	}
	
	public void addFriendUnuccesfully() {
		new WarningWindows("User not found!");
	}

}
