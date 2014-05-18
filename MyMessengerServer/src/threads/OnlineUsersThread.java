package threads;

import graphicInterfacesServer.OnlineUsersWindow;

import java.util.HashSet;
import java.util.Set;

public class OnlineUsersThread extends Thread {

	private Set<String> onlineUsers;
	private static boolean isCanceled = false;
	private static boolean listHasBeenAltered = false;
	private OnlineUsersWindow onlineUsersWindow;
	@SuppressWarnings("unused")
	private ServerThread server;

	public OnlineUsersThread(ServerThread serverThread) {
		
		onlineUsersWindow = new OnlineUsersWindow();
		//se si deschide fereastra odata cu constructorul
		onlineUsers = new HashSet<>();
		this.start();

	}

	public static synchronized void alter() {
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
