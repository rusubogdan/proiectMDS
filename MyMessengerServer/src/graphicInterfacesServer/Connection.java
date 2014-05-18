package graphicInterfacesServer;

import handlers.MessageHandler;

import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import message.Message;
import threads.MessageSender;
import threads.ServerThread;

public class Connection extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	private Socket clientSocket;
	private boolean isCanceled = false;
	private String username = null;
	private MessageSender messageSender;

	public synchronized void cancel() {
		isCanceled = true;
	}

	public synchronized String getUsername() {
		return username;
	}

	synchronized public void setUsername(String username) {
		this.username = username;
	}

	public Connection(Socket client) {
		this.clientSocket = client;
		messageSender = new MessageSender(clientSocket);
		messageSender.start();
	}

	public void run() {
		try {
			clientSocket.setSoTimeout(5000);
			ObjectInputStream inputStream = new ObjectInputStream(
					clientSocket.getInputStream());

			Object object;
			Message messageObject;

			while (!isCanceled) {

				object = null;
				try {
					object = inputStream.readObject();
				} catch (SocketTimeoutException ste) {
					continue;
				}
				
				messageObject = (Message) object;
				MessageHandler.handleMessage(messageObject, this);
			}

		} catch (EOFException eofe) {
			System.out.println("eof  connection!");
		} catch (SocketException se) {
			System.out.println("socketException!");
		} catch (ClassNotFoundException e) {
			System.out.println("clnfexception connection");
		} catch (InvalidClassException ice) {
			System.out.println("ice connection");
		} catch (StreamCorruptedException sce) {
			System.out.println("sce connection");
		} catch (IOException e) {
			System.out.println("ioexception connection");
		} catch (NullPointerException npe) {
			System.out.println("socketul este NULL");
		} finally {
			System.out.println("...........................................");
			ServerThread.removeFromOnlineUsers(username);
			ServerThread.removeConnection(this);
			this.messageSender.cancel();
			try {
				clientSocket.getInputStream().close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}

	public synchronized void addToQueueConnection(Message message) {
		messageSender.addToQueue(message);
	}

}
