package threads;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import message.Message;

public class MessageSender extends Thread {

	private boolean isCancelled = false;
	private Socket socket;
	private BlockingQueue<Message> messagesToSend;
	private ObjectOutputStream objectOutputStream;
	Message message = null;

	public MessageSender(Socket socket) {
		messagesToSend = new LinkedBlockingQueue<>();
		this.socket = socket;
		this.start();
	}

	public void addToQueue(Message message) {
		messagesToSend.add(message);
	}

	public synchronized void cancel() {
		isCancelled = true;
		System.out.println("senderul a fost inchis");
	}

	public void run() {
		try {
			System.out.println("I am activated");
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			while (!isCancelled) {
				try {
					message = messagesToSend.poll(5, TimeUnit.SECONDS);
					if (message == null)
						continue;

					objectOutputStream.writeObject(message);
					objectOutputStream.flush();
					System.out.println("I flushed a message " + message.getClass());
				} catch (InvalidClassException ice) {
					ice.printStackTrace();
				} catch (InterruptedException e) {
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
