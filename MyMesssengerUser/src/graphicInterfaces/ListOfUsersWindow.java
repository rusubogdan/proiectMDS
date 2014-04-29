package graphicInterfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import message.SignOutMessage;

public class ListOfUsersWindow {

	public JFrame frame;
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();;
	@SuppressWarnings("rawtypes")
	private JList list;
	private ChatWindow chatWindow = null;

	public void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					System.out.println("in openWindow");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListOfUsersWindow(ChatWindow chatWindow) {
		openWindow();
		this.chatWindow = chatWindow;
		initialize();
		System.out.println("in constructorul clasei ListOfUserWindow");
	}

	public synchronized static void addUsersToList(ArrayList<String> listOfUsers) {
		System.out.println("adaugare useri la lista in listOfUsersWindow");

		for (String name : listOfUsers) {
			System.out.print(name);
		}
		System.out.println();

		listModel.clear();

		for (String name : listOfUsers) {
			listModel.addElement(name);
		}
	}

	public synchronized void openChatWindow(String friend, ChatWindow chatWindow) {
		new UserChatWindow(friend, chatWindow);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private synchronized void initialize() {
		System.out.println("in initialize()");
		frame = new JFrame();
		frame.setVisible(true);
		System.out.println("dupa set Visible");
		frame.setBounds(100, 100, 283, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton button = new JButton("+");
		button.setBounds(10, 38, 41, 23);
		frame.getContentPane().add(button);

		JButton btnNewGroup = new JButton("New group");
		btnNewGroup.setBounds(65, 38, 89, 23);
		frame.getContentPane().add(btnNewGroup);

		

		list = new JList(listModel);
		list.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedValuesList().size() != 0) {

					ArrayList<Object> listOfUsers = (ArrayList<Object>) list
							.getSelectedValuesList();
					String friend = new String();

					if (listOfUsers.size() > 1)
						return;
					if (e.getClickCount() == 2) {
						friend = (String) listOfUsers.get(0);
						System.out.println("........................." + friend);
						chatWindow.openConversation(friend, chatWindow);
						openChatWindow(friend, chatWindow);

					}
				}

			}
		});

		list.setBounds(27, 89, 204, 253);
		frame.getContentPane().add(list);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				SignOutMessage message = new SignOutMessage();
				message.setName(chatWindow.name);
				chatWindow.sendMessageToServer(message);
				
				chatWindow.disconnectFromServer();
				ChatWindow.main(null);
			}
		});
		btnSignOut.setBounds(168, 38, 89, 23);
		frame.getContentPane().add(btnSignOut);
	}
}
