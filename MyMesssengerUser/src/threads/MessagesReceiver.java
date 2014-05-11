package threads;

import graphicInterfaces.AppHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;

import message.Message;

public class MessagesReceiver extends Thread {

	private Socket socket;
	private ObjectInputStream objectInputStream;
	public boolean isCanceled = false;
	private Message message;
	private AppHandler appHandler;

	public MessagesReceiver(ObjectInputStream objectInputStream, AppHandler appHandler) {
		this.objectInputStream = objectInputStream;
		this.appHandler = appHandler;
		this.start();
	}

	public Socket getSocket() {
		return this.socket;
	}

	public synchronized void cancel() {
		isCanceled = true;
		System.out.println("Receiverul a fost inchis");
	}

	public void run() {
		try {
			System.out.println("Messages receiver is online");

			while (!isCanceled) {

				message = (Message) objectInputStream.readObject();

				System.out.println("am primit :" + message.getClass());

				message.setAppHandler(appHandler);

				if (message == null)
					System.out.println("ba chiar e null");
				
				message.interactOnClient();
			}

		} catch (StreamCorruptedException sce) {
			System.out.println("StreamCorrupted Exception ");
		} catch (SocketException se) {
			System.out.println("Socket Exception");
		} catch (IOException e) {
			System.out.println("I/O Exception");
			// daca serverul a fost inchis revin la fereastra principala
		} catch (ClassNotFoundException cnfe) {
			System.out.println("ClassNotFound Exception");
		} finally {
			if (message == null)
				System.out.println("ba chiar e null");
//			this.cancel();
			// appHandler.disconnectFromServer();
		}
		
		// aici ar trebui sa inchid streamul si socketul
		
		
		
	}

}
