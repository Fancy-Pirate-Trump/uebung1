package problem4;

import java.util.Random;

public class Acquisition implements Runnable {
	WaitingQueue queue;
	Random rand = new Random();
	public Acquisition(WaitingQueue q) {
		queue = q;
	}

	@Override
	public void run() {
		synchronized(queue) {
			while(queue.acceptsCustomers()) {
				System.out.println("Akquiriere Kunden...");
				try {
					Thread.sleep(rand.nextInt(2000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				queue.enqueue(new Customer());
				System.out.println("Kunde akquiriert!");
				while(queue.size() > 0) {
					queue.dequeue();
				}
			}
		}
	}
}
