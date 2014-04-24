package threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import message.Message;

public class MessageReceiver extends Thread {

	private Socket socket;
	private ObjectInputStream objectInputStream;
	public static boolean isCanceled = false;
	private Message message;

	public MessageReceiver(Socket socket) {
		this.socket = socket;
		this.start();
	}

	public Socket getSocket() {
		return this.socket;
	}

	public static synchronized void cancel() {
		isCanceled = true;
	}

	public void run() {
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Client receiver is online");
			while (!isCanceled) {
				message = (Message) objectInputStream.readObject();
				message.interactOnClient();
			}

		} catch (IOException e) {
			e.printStackTrace();
			MessageReceiver.cancel();
			//daca serverul a fost inchis revin la fereastra principala
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//nu are de ce sa imi intre aici
		}
	}

}
