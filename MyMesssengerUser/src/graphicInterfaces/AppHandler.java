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
import message.SignOutMessage;
import threads.MessagesReceiver;

public class AppHandler extends Thread {

	private String serverAddress = "localhost";
	private int serverPort = 9999;
	private Socket socket;
	public ObjectOutputStream objectOutputStream;
	public ObjectInputStream objectInputStream;
	private MessagesReceiver messagesReceiver;
	private AppMainWindow appMainWindow;
	private String nameOfUser;
	public ListOfUsersWindow listOfUsersWindow;
	private BlockingQueue<Message> messagesToSend;
	private BlockingQueue<String> nothing;
	private boolean isCanceled = false;
	private Message message;
	public boolean isRunning = false;
	private boolean online = false;

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

		AppMainWindow.main(null); 
		AppMainWindow.setAppHandler(this);

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
			e.printStackTrace();
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

				System.out.println("Am trimis :" + message.getClass());

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

			this.cancel();
			// appHandlerClass.disconnectFromServer();

			// inchid toate resursele
		}

	}

	public synchronized void cancel() {
		isCanceled = true;
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
		AppMainWindow.closeWindow();
		listOfUsersWindow = new ListOfUsersWindow();
		listOfUsersWindow.setAppHandler(this);
	}

	public void signInUnsuccesfully() {
		nameOfUser = "";
		appMainWindow.signInUnsuccesfully();
	}

	// la signOut se apeleaza din ListOfUsersWindow
	public void disconnectFromServer() {
		
		listOfUsersWindow.closeWindow();
		
		try {
			System.out.println("DISCONNECT FROM SERVER!");
			messagesReceiver.cancel();
			objectOutputStream.close();
			socket.close();

			AppMainWindow.main(null);
			AppMainWindow.setAppHandler(this); 
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		online = false;
		
	}

	public void signOut() {
		
		SignOutMessage msg = new SignOutMessage(nameOfUser);
		addMessageToQueue(msg);
		
		disconnectFromServer();
		
	}


}
