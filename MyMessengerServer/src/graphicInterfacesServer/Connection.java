package graphicInterfacesServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JTextField;

import com.entities.User;

import message.Message;
import threads.MessageSender;
import threads.MessageThread;

public class Connection extends Thread {
	private Socket clientSocket;
	private ObjectInputStream inputStream;
	private JTextField textField;
	private Message messageObject;
	private static boolean isCanceled = false;
	private User user = null;
	private static MessageSender messageSender;

	private Object object;

	public static synchronized void cancel() {
		isCanceled = true;
	}

	public synchronized boolean connectionClosed() {
		return isCanceled;
	}

	public synchronized Socket getSocket() {
		return clientSocket;
	}

	public User getUser() {
		return user;
	}

	// daca user-ul e null inseamna ca nu s-a definitivat conexiunea cu un user
	// ex: inca nu s-a logat sau inca nu s-a inregistrat

	public void setUser(User user) {
		this.user = user;
	}

	public Connection(Socket client, JTextField textField) {

		this.clientSocket = client;
		this.textField = textField;
		messageSender = new MessageSender(clientSocket);

		this.start();
	}

	public void run() {
		try {
			inputStream = new ObjectInputStream(clientSocket.getInputStream());

			while (!isCanceled) {
				try {
					object = inputStream.readObject();
					System.out.println((object.getClass()) + " aici ");
					messageObject = (Message) object;
					//MessageThread.addToQueueMess(message, this, connectionOfReceiver);
					messageObject.interactOnServer();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

	}

	public synchronized void addToQueueConnection(Message message) {
		messageSender.addToQueue(message);
	}

}
