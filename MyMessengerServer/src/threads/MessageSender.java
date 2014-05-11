package threads;

import graphicInterfacesServer.Connection;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import message.ListOfFriendsMessage;
import message.Message;

public class MessageSender extends Thread {

	private boolean isCancelled = false;
	private Socket socket;
	private BlockingQueue<Message> messagesToSend;
	private ObjectOutputStream objectOutputStream;
	private Message message = null;
	private Connection connection;

	public MessageSender(Socket socket, Connection connection) {
		messagesToSend = new LinkedBlockingQueue<>();
		this.socket = socket;
		this.connection = connection;
		this.start();
	}

	public synchronized void addToQueue(Message message) {
		messagesToSend.add(message);
	}

	public synchronized void cancel() {
		isCancelled = true;
		System.out.println("senderul a fost inchis");
	}

	public void run() {
		try {

			System.out.println("MessageSender-ul e activat");

			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			while (!isCancelled) {
				try {
					message = messagesToSend.poll(5, TimeUnit.SECONDS);
					if (message == null)
						continue;
					
					ListOfFriendsMessage lst = new ListOfFriendsMessage();

					if (message.getClass().equals(ListOfFriendsMessage.class)) {

						lst = (ListOfFriendsMessage) message;

						System.out.println("Trimit lui: "
								+ this.connection.getUser().getUsername() + " lista");

						ArrayList<String> fr = new ArrayList<String>();

						for (String u : lst.getFriendsByName()) {
							System.out.print(u + " ");
							fr.add(u);

						}
						System.out.println();
						lst.setFriendsByName(fr);
						message = lst;
					}
					System.out.println("Trimit lui: "
						//	+ this.connection.getUser().getUsername() + " un "
							+ message.getClass());
					
					if (message == null)
						System.out.println("trimit un mesaj nullllllll");
					
					objectOutputStream.writeObject(message);
					objectOutputStream.flush();

				} catch (InvalidClassException ice) {
					ice.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("IOE in messageSender");
			e.printStackTrace();
		} catch (NullPointerException npe) {
			npe.printStackTrace();
		} 
	}

}
