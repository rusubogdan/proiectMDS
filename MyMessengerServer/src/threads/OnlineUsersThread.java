package threads;

import graphicInterfacesServer.OnlineUsersWindow;

import java.util.ArrayList;

import com.entities.User;

public class OnlineUsersThread extends Thread {

	private ArrayList<User> onlineUsers;
	private static boolean isCanceled = false;
	private static boolean listHasBeenAltered = false;
	private OnlineUsersWindow onlineUsersWindow;
	@SuppressWarnings("unused")
	private ServerThread server;

	public OnlineUsersThread(ServerThread serverThread) {
		
		onlineUsersWindow = new OnlineUsersWindow();
		//se si deschide fereastra odata cu constructorul
		onlineUsers = new ArrayList<User>();
		this.start();

	}

	public static synchronized void alter() {
		System.out.println("altering....");
		listHasBeenAltered = true;
	}
	
	public static void cancel() {
		isCanceled = true;
	}

	public void run() {

		while (!isCanceled) {
			if (listHasBeenAltered) {
				onlineUsers = ServerThread.getOnlineUsers();
				onlineUsersWindow.showOnlineUsersList(onlineUsers);
				listHasBeenAltered = false;
				
			}
		}
		onlineUsersWindow.closeOnlineUsersList();
	}

}
