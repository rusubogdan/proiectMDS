package graphicInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import message.RequestFriendInfo;
import message.RequestedFriendInfo;

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
					if (user.getFriend().equals(sender)) {
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

	public static synchronized void addUsersToList(List<String> listOfUsers) {

		listModel.clear();

		for (String name : listOfUsers) {
			listModel.addElement(name);
		}
	}

	public static synchronized void addUserToList(String user) {
		listModel.addElement(user);
	}

	public static synchronized void removeUserFromList(String user) {
		listModel.removeElement(user);
	}

	private void addFriend() {
		@SuppressWarnings("unused")
		AddFriendWindow addFriendWindow = new AddFriendWindow(this);
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

		JButton btnAddFriend = new JButton("Add friend");
		btnAddFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFriend();
			}

		});
		btnAddFriend.setBounds(27, 29, 105, 30);
		frame.getContentPane().add(btnAddFriend);

		JButton btnInfo = new JButton("Friend Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Object> listOfUsers = (ArrayList<Object>) list
						.getSelectedValuesList();
				String friend = new String();

				RequestFriendInfo requestFriendInfo;
				
				if (listOfUsers.size() == 1) {
					requestFriendInfo = new RequestFriendInfo();

					friend = (String) listOfUsers.get(0);
					
					requestFriendInfo.setUser(friend);
					
					appHandler.addMessageToQueue(requestFriendInfo);
					
				}

			}
		});
		btnInfo.setBounds(126, 85, 105, 23);
		frame.getContentPane().add(btnInfo);

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

						addToUsers(new UserChatWindow(friend, appHandler));

					}
				}

			}
		});

		list.setBounds(27, 119, 204, 223);
		frame.getContentPane().add(list);

		JButton btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appHandler.signOut();

				try {
					sleep(3000);
				} catch (InterruptedException ee) {
					ee.printStackTrace();
				}

				frame.dispose();
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				appHandler.signOut();

				try {
					sleep(3000);
				} catch (InterruptedException ee) {
					ee.printStackTrace();
				}

				frame.dispose();
			}
		});

		btnSignOut.setBounds(173, 29, 84, 30);
		frame.getContentPane().add(btnSignOut);
		
		JButton btnInfo_1 = new JButton("Info");
		btnInfo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new UpdateInfoWindow(appHandler);
				
				
			}
		});
		btnInfo_1.setBounds(27, 85, 89, 23);
		frame.getContentPane().add(btnInfo_1);
	}

	public void closeWindow() {
		frame.dispose();
	}

	public static synchronized void addMessageToMessageQueue(ChatMessage message) {
		messages.add(message);
	}

	public void addFriend(String friend) {
		AddFriendMessage addFriend = new AddFriendMessage(appHandler.getUsername(),
				friend);
		appHandler.addMessageToQueue(addFriend);
	}

	public synchronized void serverHasBeenClosed() {
		for (UserChatWindow chatWindow : usersInChat) {
			chatWindow.closeConversation();
		}

		closeWindow();

	}

	public void userAlreadyFriend() {
		new WarningWindows("You are friend with this user");
	}

	public void showInfo(RequestedFriendInfo requestedFriendInfo) {
		
		new FriendInfoWindow(requestedFriendInfo);
		
	}
}
