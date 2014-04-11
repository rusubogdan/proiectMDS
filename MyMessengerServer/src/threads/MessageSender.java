package threads;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import message.Message;

public class MessageSender extends Thread {

	private boolean isCancelled = false;
	private Socket socket;
	private BlockingQueue<Message> blockingQueue;
	private ObjectOutputStream objectOutputStream;

	public MessageSender(Socket socket) {
		this.socket = socket;

	}

	public void addToQueue(Message message) {
		blockingQueue.add(message);
	}
	
	public void cancel() {
		isCancelled = true;
	}
	
	
	public void run() {
		try {
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			Message message;

			while (!isCancelled) {
				try {
					message = blockingQueue.poll(5, TimeUnit.SECONDS);
					if(message.message == null) continue;
					objectOutputStream.writeObject(message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
