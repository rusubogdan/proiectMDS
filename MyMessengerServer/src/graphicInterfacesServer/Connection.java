package graphicInterfacesServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JTextField;

import message.Message;
import threads.MessageSender;

import com.entities.User;

public class Connection extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	private Socket clientSocket;
	private ObjectInputStream inputStream;
	@SuppressWarnings("unused")
	private JTextField textField;
	private Message messageObject;
	private boolean isCanceled = false;
	private User user = null;
	private static MessageSender messageSender;

	private Object object;

	public synchronized void cancel() {
		isCanceled = true;
		messageSender.cancel();
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
	// ex: inca nu s-a logat sau inca nu s-a inregistrat

	synchronized public void setUser(User user) {
		this.user = user;
	}

	public Connection(Socket client, JTextField textField) {

		this.clientSocket = client;
		this.textField = textField;
		this.start();
		messageSender = new MessageSender(clientSocket);
	}

	public void run() {
		try {
			inputStream = new ObjectInputStream(clientSocket.getInputStream());

			while (!isCanceled) {
				try {
					object = inputStream.readObject();
					System.out.println((object.getClass()) + " aici ");
					messageObject = (Message) object;
					messageObject.interactOnServer(this, null);
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println("ioexception");
					this.cancel();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					System.out.println("clnfexception");
					
				} finally {
					System.out.println("conexiunea a fost inchisa");
				}

			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println("conexiunea a fost inchisa ; user-ul s-a deconectat");
			this.cancel();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
			System.out.println("socketul nu este bun");
			this.cancel();
		}

	}

	public synchronized void addToQueueConnection(Message message) {
		messageSender.addToQueue(message);
	}

}
