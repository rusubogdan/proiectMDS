package threads;

import graphicInterfacesServer.Connection;
import graphicInterfacesServer.MessagesPanel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import message.Message;

public class MessageThread extends Thread {

	private boolean isCancelled = false;
	private Message message = null;
	private static Connection connectionOfSender = null;
	private static Connection connectionOfReceiver = null;
	private static BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<Message>();

	public void cancel() {
		isCancelled = true;
	}

	public static synchronized void addToQueueMess(Message message,
			Connection connectionOfSender, Connection connectionOfReceiver) {
		blockingQueue.add(message);
		MessageThread.connectionOfSender = connectionOfSender;
		MessageThread.connectionOfReceiver = connectionOfReceiver;
		//schimb conexiunea la care se trimite mesajul actual
	}

	public MessageThread() {
		this.start();
	}

	public void run() {

		while (!isCancelled) {
			try {
				message = blockingQueue.poll(10, TimeUnit.SECONDS);
				if (message == null)
					continue;
				MessagesPanel.addMessageToMessageSender(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
