package graphicInterfaces;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JTextField;

import com.entities.User;

import message.Message;
import requests.Request;
import threads.MessageSender;

public class Connection extends Thread {
	private Socket clientSocket;
	private ObjectInputStream inputStream;
	private JTextField textField;
	private static Message message;
	private static boolean isCanceled = false;
	private User user;
	private static MessageSender messageSender;

	private static Object object;

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

	public void setUser(User user) {
		this.user = user;
	}
	
	public Connection(Socket client, JTextField textField) {

		this.clientSocket = client;
		this.textField = textField;
		Connection.messageSender = new MessageSender(clientSocket);

		this.start();
	}

	private void requestType(Object object) {
		
	}

	private void messageType(Object object) {
		String string = new String();
		message = (Message) object;
		string = string + message.message + "\n";
		textField.setText(string);

	}

	public void run() {
		Object obClass;
		try {
			inputStream = new ObjectInputStream(clientSocket.getInputStream());

			while (!isCanceled) {
				try {
					object = inputStream.readObject();
					System.out.println(obClass = object.getClass());

					if (obClass == Request.class)
						requestType(object);
					else
						messageType(object);

				} catch (Exception e) {

				}

			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		}

	}

	public static synchronized void addToQueueConnection(Message message) {
		messageSender.addToQueue(message);
	}


}
