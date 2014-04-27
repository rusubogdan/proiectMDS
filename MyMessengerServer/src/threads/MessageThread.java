package threads;

import graphicInterfacesServer.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import message.Message;

/*
 * Clasa primeste mesaje in coada sa de mesaje si ,folosindu-se de 
 * conexiunile sender/receiver din mesaj, stie in coada de mesaje
 * a carei conexiuni sa puna respectivul mesaj
 */

public class MessageThread extends Thread {

	private boolean isCancelled = false;
	private Message message = null;
	private static Connection connectionOfReceiver = null;
	private static BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<Message>();

	public synchronized void cancel() {
		isCancelled = true;
	}

	public static synchronized void addToQueueMess(Message message,
			Connection connOfSender, Connection connOfReceiver) {
		connectionOfReceiver = connOfReceiver;
		blockingQueue.add(message);
	}
	
	

	public MessageThread() {
		this.start();
	}

	public void run() {
		System.out.println("MessageThread is online!");
		while (!isCancelled) {
			try {
				message = blockingQueue.poll(10, TimeUnit.SECONDS);
				if (message == null)
					continue;
				connectionOfReceiver.addToQueueConnection(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
