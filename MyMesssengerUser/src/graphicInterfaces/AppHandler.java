package graphicInterfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import message.Message;
import message.RequestedFriendInfo;
import message.SignOutMessage;
import threads.MessagesReceiver;

public class AppHandler extends Thread {

	private String serverAddress = "149.213.41.100";
	private int serverPort = 9999;
	private Socket socket;
	public ObjectOutputStream objectOutputStream;
	public ObjectInputStream objectInputStream;
	private MessagesReceiver messagesReceiver;
	private String nameOfUser;
	public ListOfUsersWindow listOfUsersWindow;
	private BlockingQueue<Message> messagesToSend;
	private BlockingQueue<String> nothing;
	private boolean isCanceled = false;
	private Message message;
	public boolean isRunning = false;
	private boolean online = false;
	private AppMainWindow appMainWindow;

	public static void main(String args[]) {
		new AppHandler();
	}

	public String getUsername() {
		return nameOfUser;
	}

	public AppHandler() {
		nameOfUser = new String();
		messagesToSend = new LinkedBlockingQueue<Message>();
		nothing = new LinkedBlockingQueue<String>();
		appMainWindow = new AppMainWindow();
		appMainWindow.setAppHandler(this);

		run();

	}

	public synchronized void addMessageToQueue(Message message) {
		messagesToSend.add(message);
	}

	public void connectToServer() {

		try {
			socket = new Socket(serverAddress, serverPort);

			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());

			System.out.println("AppHandler is online and ready to send messages");

			messagesReceiver = new MessagesReceiver(objectInputStream, this);

		} catch (IOException e) {
			System.out.println("Server offline!");
		}

		online = true;

	}

	public void run() {
		String ntg;
		try {

			while (!isCanceled) {

				while (!online) {

					ntg = nothing.poll(2, TimeUnit.SECONDS);
					if (ntg == null)
						continue;

				}

				message = messagesToSend.poll(5, TimeUnit.SECONDS);

				if (message == null)
					continue;

				objectOutputStream.writeObject(message);
				objectOutputStream.flush();

				if (message.getClass().equals(SignOutMessage.class)) {
					try {
						sleep(5000);
					} catch (InterruptedException e) {

					}
					System.out.println("A intrat in signu outtttttttttttttttttttttt");
					disconnectFromServer();
				}

			}

		} catch (StreamCorruptedException sce) {
			System.out.println("Stream corrupted exception ");
		} catch (IOException e) {
			System.out.println("IOE  Serverul a fost inchis");
			// daca serverul a fost inchis revin la fereastra principala
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Server or connection to server has been closed!");
		} finally {

			// appHandlerClass.disconnectFromServer();
			// inchid toate resursele
		}

	}

	public void setNameOfUser(String name) {
		nameOfUser = name;
	}

	public void signUpSuccesfully() {
		appMainWindow.signUpSuccesfully();
	}

	public void signUpUnsuccesfully() {
		appMainWindow.signUpUnsuccesfully();
	}

	public void signInSuccesfully() {
		System.out.println("SignInSuccesfully");
		appMainWindow.closeWindow();
		listOfUsersWindow = new ListOfUsersWindow();
		listOfUsersWindow.setAppHandler(this);
	}

	public void signInUnsuccesfully() {
		nameOfUser = "";
		appMainWindow.signInUnsuccesfully();
	}

	// la signOut se apeleaza din ListOfUsersWindow
	public void disconnectFromServer() {

		try {
			System.out.println("DISCONNECTING FROM SERVER...!");

			try {
				sleep(2000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

			System.out.println("DISCONNECTED FROM SERVER!");

			appMainWindow = new AppMainWindow();
			appMainWindow.setAppHandler(this);

			messagesReceiver.cancel();
			objectOutputStream.close();
			socket.close();


		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		online = false;
	}

	public void signOut() {

		SignOutMessage signOutMessage = new SignOutMessage();

		signOutMessage.setUser(nameOfUser);

		addMessageToQueue(signOutMessage);

	}

	public void addFriendSuccesfully() {
		appMainWindow.addFriendSuccesfully();
	}

	public void addFriendUnuccesfully() {
		appMainWindow.addFriendUnuccesfully();
	}

	public void serverHasBeenClosed() {
		listOfUsersWindow.serverHasBeenClosed();
		
		appMainWindow = new AppMainWindow();
		appMainWindow.setAppHandler(this);

	}

	public void userAlreadyFriend() {
		listOfUsersWindow.userAlreadyFriend();
	}

	public void showFriendInfo(RequestedFriendInfo requestedFriendInfo) {
		listOfUsersWindow.showInfo(requestedFriendInfo);
	}

}
