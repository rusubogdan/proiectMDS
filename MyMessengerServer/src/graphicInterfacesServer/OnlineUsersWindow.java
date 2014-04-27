package graphicInterfacesServer;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import com.entities.User;

import javax.swing.JList;
import javax.swing.JTree;

public class OnlineUsersWindow  {

	private static JFrame frame;
	public DefaultListModel<String> listModel;
	public JList listOfOnlineUsers;

	/**
	 * Launch the application.
	 */
	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//OnlineUsersWindow window = new OnlineUsersWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public synchronized void showOnlineUsersList(ArrayList<User> onlineUsers) {
		listModel.clear();
		for(User user : onlineUsers) {
			listModel.addElement(user.getUsername());
		}
	}

	public synchronized void closeOnlineUsersList() {
		closeFrame();
	}

	private synchronized void closeFrame() {
		System.out.println("a intrat in close");
		frame.setVisible(false);
		frame.dispose();
	}

	public OnlineUsersWindow() {
		openWindow();
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
		
		listModel = new DefaultListModel<String>();
		listOfOnlineUsers = new JList(listModel);
		
		listOfOnlineUsers.setBounds(29, 11, 269, 217);
		frame.getContentPane().add(listOfOnlineUsers);
	}
}
