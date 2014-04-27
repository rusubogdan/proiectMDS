package threads;

import graphicInterfaces.ChatWindow;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

import message.Message;

public class MessageReceiver extends Thread {

	private Socket socket;
	private ObjectInputStream objectInputStream;
	public static boolean isCanceled = false;
	private Message message;
	private ChatWindow chatWindow;

	public MessageReceiver(Socket socket, ChatWindow chatWindow) {
		this.socket = socket;
		this.chatWindow = chatWindow;
		this.start();
	}

	public Socket getSocket() {
		return this.socket;
	}

	public static synchronized void cancel() {
		isCanceled = true;
		System.out.println("Receiverul a fost inchis");
	}

	public synchronized void run() {
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Client receiver is online");
			while (!isCanceled) {
				message = (Message) objectInputStream.readObject();
				System.out.println("am primit :" + message.getClass());
				message.interactOnClient(chatWindow);
			}

		} catch (StreamCorruptedException sce) {
			System.out.println("Stream corrupted exception ");
		} catch (IOException e) {
			MessageReceiver.cancel();
			System.out.println("IOE  Serverul a fost inchis");
			// daca serverul a fost inchis revin la fereastra principala
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("class not found exception");
			// nu are de ce sa imi intre aici
		} catch (Exception e) {
			System.out.println("Server or connection to server has been closed!");
		} finally {
			MessageReceiver.cancel();
			ChatWindow.closeAll();
		}
	}

}
