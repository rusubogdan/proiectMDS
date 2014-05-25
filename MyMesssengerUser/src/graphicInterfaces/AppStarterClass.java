package graphicInterfaces;


public class AppStarterClass {

	
	public static void main(String[] args) {
		new AppHandler();
//		new test();
		
		
		
		
	}
}

//package graphicInterfaces;
//
//import java.awt.Color;
//
//public class AppMainWindow {
//
//	private static JFrame frame;
//	private JTextField passwordTF;
//	private JTextField confirmPasswordTF;
//	private JTextField usernameTF;
//	public String name;
//	public ListOfUsersWindow listOfUsersWindow;
//	private static AppHandler appHandler;
//
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					@SuppressWarnings("unused")
//					AppMainWindow window = new AppMainWindow();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	public static synchronized void closeWindow() {
//		frame.dispose();
//	}
//
//	public void clearFields() {
//		usernameTF.setText("");
//		passwordTF.setText("");
//		confirmPasswordTF.setText("");
//	}
//
//	private AppMainWindow() {
//		initialize();
//	}
//
//	public static void setAppHandler(AppHandler handler) {
//		appHandler = handler;
//	}
//
//	private synchronized void initialize() {
//		frame = new JFrame();
//
//		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
//		frame.setBounds(100, 100, 257, 358);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//
//		usernameTF = new JTextField();
//		usernameTF.setBounds(31, 110, 154, 20);
//		frame.getContentPane().add(usernameTF);
//		usernameTF.setColumns(10);
//
//		passwordTF = new JTextField();
//		passwordTF.setBounds(31, 157, 154, 20);
//		frame.getContentPane().add(passwordTF);
//		passwordTF.setColumns(10);
//
//		confirmPasswordTF = new JTextField();
//		confirmPasswordTF.setBounds(31, 204, 154, 20);
//		frame.getContentPane().add(confirmPasswordTF);
//		confirmPasswordTF.setColumns(10);
//
//		frame.setVisible(true);
//
//		passwordTF.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//					String username = new String(), password = new String();
//					username = usernameTF.getText();
//					password = passwordTF.getText();
//
//					if (username.equals("")) {
//						new WarningWindows("Username field is empty!");
//					} else {
//						if (password.equals("")) {
//							new WarningWindows("Password field is empty!");
//						} else {
//
//							appHandler.connectToServer();
//
//							Message signInMessage = new SignInMessage(username, password);
//							appHandler.setNameOfUser(username);
//
//							appHandler.addMessageToQueue(signInMessage);
//
//						}
//					}
//				}
//			}
//		});
//
//		JButton btnSignUp = new JButton("Sign Up");
//		btnSignUp.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				String username = new String(), password = new String(), passwordConfirm = new String();
//
//				username = usernameTF.getText();
//				password = passwordTF.getText();
//				passwordConfirm = confirmPasswordTF.getText();
//
//				if (username.equals("")) {
//					new WarningWindows("Username field is empty!");
//				} else {
//					if (password.equals("") || passwordConfirm.equals("")
//							|| !password.equals(passwordConfirm)) {
//						new WarningWindows("Password field is empty!");
//					} else {
//
//						appHandler.connectToServer();
//
//						Message signUpMessage = new SignUpMessage(username, password);
//						appHandler.addMessageToQueue(signUpMessage);
//
//					}
//				}
//
//			}
//		});
//		btnSignUp.setBounds(57, 270, 98, 23);
//		frame.getContentPane().add(btnSignUp);
//
//		JButton btnSignIn = new JButton("Sign In");
//		btnSignIn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String username = new String(), password = new String();
//				username = usernameTF.getText();
//				password = passwordTF.getText();
//
//				if (username.equals("")) {
//					new WarningWindows("Username field is empty!");
//				} else {
//					if (password.equals("")) {
//						new WarningWindows("Password field is empty!");
//					} else {
//
//						appHandler.connectToServer();
//
//						Message signInMessage = new SignInMessage(username, password);
//
//						appHandler.setNameOfUser(username);
//
//						appHandler.addMessageToQueue(signInMessage);
//
//					}
//				}
//
//			}
//		});
//		btnSignIn.setBounds(57, 235, 98, 23);
//		frame.getContentPane().add(btnSignIn);
//
//		JLabel lblUsername = new JLabel("Username:");
//		lblUsername.setBounds(31, 92, 98, 14);
//		frame.getContentPane().add(lblUsername);
//
//		JLabel lblPassword = new JLabel("Password:");
//		lblPassword.setBounds(31, 141, 68, 14);
//		frame.getContentPane().add(lblPassword);
//
//		JLabel lblPasswordAgain = new JLabel("Password again:");
//		lblPasswordAgain.setBounds(31, 188, 98, 14);
//		frame.getContentPane().add(lblPasswordAgain);
//	}
//
//	public static void signUpSuccesfully() {
//		new WarningWindows("SignUp succesfully!");
//	}
//
//	public static void signInUnsuccesfully() {
//		new WarningWindows("User and password do not match!");
//	}
//
//	public static void signUpUnsuccesfully() {
//		new WarningWindows("User already exists!");
//	}
//
//}
