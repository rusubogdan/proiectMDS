package threads;

import graphicInterfaces.AppHandler;
import handlers.MessageHandler;
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
	}

	public void run() {
		try {

			while (!isCanceled) {

				message = (Message) objectInputStream.readObject();

				MessageHandler.handleMessage(message, appHandler);
				
				System.out.println("am primit:" + message.getClass().getName());

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
			System.out.println("Receiverul a fost inchis");
		}
		
	}

}
