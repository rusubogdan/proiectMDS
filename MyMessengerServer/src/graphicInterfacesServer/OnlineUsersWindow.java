package graphicInterfacesServer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import threads.ServerThread;

import com.entities.User;

public class OnlineUsersWindow  {

	private static JFrame frame;
	public DefaultListModel<String> listModel;
	@SuppressWarnings("rawtypes")
	public JList listOfOnlineUsers;
	private JTextField textField;
	private JTextField txtDate;

	public static void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		listModel = new DefaultListModel<String>();
		listOfOnlineUsers = new JList(listModel);
		
		listOfOnlineUsers.setBounds(10, 47, 232, 278);
		frame.getContentPane().add(listOfOnlineUsers);
		
		JLabel lblOnlineUsers = new JLabel("Online users:");
		lblOnlineUsers.setBounds(10, 11, 105, 25);
		frame.getContentPane().add(lblOnlineUsers);
		
		JButton btnNewButton = new JButton("Stop Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				ServerThread.removeConnection(null); 
				StartServerWindow.stopTheServer(); 
			}
		});
		btnNewButton.setBounds(252, 212, 172, 113);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(252, 87, 172, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(new Date().toString());
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setText("Date");
		txtDate.setBounds(252, 56, 172, 20);
		frame.getContentPane().add(txtDate);
		txtDate.setColumns(10);
	}
}
