package graphicInterfacesServer;

import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;

import message.Message;
import threads.MessageSender;
import threads.ServerThread;

import com.entities.User;

public class Connection extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	private Socket clientSocket;
	private ObjectInputStream inputStream;
	private Message messageObject;
	private boolean isCanceled = false;
	private User user = null;
	private MessageSender messageSender;
	private Object object;

	public synchronized void cancel() {
		isCanceled = true;
	}

	public synchronized boolean connectionClosed() {
		return isCanceled;
	}

	public synchronized Socket getSocket() {
		return clientSocket;
	}

	public synchronized User getUser() {
		return user;
	}

	// daca user-ul e null inseamna ca nu s-a definitivat conexiunea cu un user
	// ex: inca nu s-a logat

	synchronized public void setUser(User user) {
		this.user = user;
	}

	public Connection(Socket client) {

		this.clientSocket = client;
		messageSender = new MessageSender(clientSocket, this);

		this.start();
	}

	public void run() {
		try {
			inputStream = new ObjectInputStream(clientSocket.getInputStream());

			try {

				while (!isCanceled) {

					object = null;
					object = inputStream.readObject();

					System.out.println(("Am citit un : " + object.getClass()));

					messageObject = (Message) object;
					messageObject.setConnectionOfSender(this);
					messageObject.setConnectionOfReceiver(null);
					messageObject.interactOnServer(this, null);
					// !!! sa ii schimb la interact simplu fara parametri

				}
			} catch (EOFException eofe) {
				System.out.println("eof  connection!");
			} catch (SocketException se) {
				System.out.println("socketException!");
				ServerThread.removeFromOnlineUsersQueue(user);
			} catch (ClassNotFoundException e) {
				System.out.println("clnfexception connection");

			} catch (InvalidClassException ice) {
				System.out.println("ice connection");

			} catch (StreamCorruptedException sce) {
				System.out.println("sce connection");

			} catch (IOException e) {
				System.out.println("ioexception connection");
			} finally {
//				if (user != null)
//					ServerThread.removeFromOnlineUsersQueue(user);
				
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("conexiunea a fost inchisa ; user-ul s-a deconectat");
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			System.out.println("socketul este NULL");
		} finally {
			System.out.println("...........................................");
			// aici eliberez resursele folosite
//			ServerThread.removeConnection(this);
//			messageSender.cancel(); 
		}

	}

	public synchronized void addToQueueConnection(Message message) {
		messageSender.addToQueue(message);
	}

}
