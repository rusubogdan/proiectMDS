package graphicInterfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ListOfUsersWindow {

	private JFrame frame;
	private JTextField txtListOfUsers;

	/**
	 * Launch the application.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListOfUsersWindow window = new ListOfUsersWindow();
					window.frame.setVisible(true);
					System.out.println("in openWindow");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListOfUsersWindow() {
		initialize();
		System.out.println("in constructor");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("in initialize()");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtListOfUsers = new JTextField();
		txtListOfUsers.setText("List of users");
		txtListOfUsers.setBounds(10, 31, 86, 20);
		frame.getContentPane().add(txtListOfUsers);
		txtListOfUsers.setColumns(10);
	}
}
