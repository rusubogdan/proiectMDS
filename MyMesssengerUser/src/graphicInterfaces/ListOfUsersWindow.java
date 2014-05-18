package graphicInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import message.AddFriendMessage;
import message.ChatMessage;

public class ListOfUsersWindow extends Thread {

	public JFrame frame;
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();;
	@SuppressWarnings("rawtypes")
	private JList list;
	private AppHandler appHandler;
	private boolean isClosed = false;
	private static BlockingQueue<ChatMessage> messages;
	private ChatMessage message;
	private static ArrayList<UserChatWindow> usersInChat;

	public void setAppHandler(AppHandler appHandler) {
		this.appHandler = appHandler;
	}

	public synchronized static void addToUsers(UserChatWindow userChat) {
		boolean b = usersInChat.add(userChat);
		System.out.println(b + "add");
	}

	public synchronized static void removeFromUsers(UserChatWindow userChat) {
		boolean b = usersInChat.remove(userChat);
		System.out.println(b + "remove");
	}

	public ListOfUsersWindow() {
		System.out.println("in constructorul clasei ListOfUserWindow");
		messages = new LinkedBlockingQueue<ChatMessage>();
		usersInChat = new ArrayList<UserChatWindow>();
		initialize();
		start();
	}

	public synchronized void close() {
		isClosed = true;
	}

	public void run() {

		boolean finded = false;

		try {
			while (!isClosed) {

				message = messages.poll(3, TimeUnit.SECONDS);

				frame.setTitle(appHandler.getUsername());

				if (message == null)
					continue;

				String sender = message.getUser();
				
				finded = false;

				for (UserChatWindow user : usersInChat) {
					System.out.println("IN FOR ININTE DE IF");
					if (user.getFriend().equals(sender)) {
						System.out.println("IN FOR DUPA IF");
						user.setMessage(message.getUser() + ": " + message.getMessage());
						finded = true;
						break;
					}
				}

				if (!finded) {
					UserChatWindow senderUser;
					senderUser = new UserChatWindow(sender, appHandler);
					addToUsers(senderUser);
					senderUser.setMessage(sender + ": " + message.getMessage());

				}

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public synchronized static void addUsersToList(List<String> listOfUsers) {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private synchronized void initialize() {
		System.out.println("in initialize() din ListOfUsersWindow");
		frame = new JFrame();
		frame.setVisible(true);
		System.out.println("dupa set Visible");
		frame.setBounds(100, 100, 283, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriendMessage addFriend = new AddFriendMessage(appHandler
						.getUsername(), "mihai");
				appHandler.addMessageToQueue(addFriend);
			}
		});
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

						addToUsers(new UserChatWindow(friend, appHandler));

					}
				}

			}
		});

		list.setBounds(27, 89, 204, 253);
		frame.getContentPane().add(list);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appHandler.signOut();
				frame.dispose();

			}
		});
		btnSignOut.setBounds(168, 38, 89, 23);
		frame.getContentPane().add(btnSignOut);
	}

	public void closeWindow() {
		frame.dispose();
	}

	public static synchronized void addMessageToMessageQueue(ChatMessage message) {
		messages.add(message);
	}
}
