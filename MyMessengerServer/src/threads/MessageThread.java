package threads;

import graphicInterfaces.MessagesPanel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import message.Message;

public class MessageThread extends Thread {

	private boolean isCancelled = false;
	private static BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<Message>();;

	public void cancel() {
		isCancelled = true;
	}

	public MessageThread() {

		this.start();
	}

	public void run() {
		Message message;
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
