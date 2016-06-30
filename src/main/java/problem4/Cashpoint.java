package problem4;

import java.util.LinkedList;
import java.util.Random;

public class Cashpoint implements Runnable, Comparable<Cashpoint>{
	private Random rand = new Random();
	private LinkedList<Customer> customers = new LinkedList<>();
	private int id;
	private int myTotal;
	private Balance balance;
	private boolean isOpen;


	public Cashpoint(int id) {
		this.id = id;
	}

	public boolean enqueue(Customer c) {
		System.out.println("Kunde an Kasse " + id + " eingereiht.");
		return customers.add(c);
	}

	public int getQueueLength(){
		return customers.size();
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void open() {
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {

		try {
			isOpen = true; //Die Kasse Ist vor dem Warten schon offen, damit sich Kunden anstellen k�nnen
			System.out.println("Kasse " + id + " wird ge�ffnet...");
			Thread.sleep(6000);  //Das �ffnen einer Kasse dauert 6 Sekunden
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("Kasse " + id + " ist geöffnet.");
		while(customers.size() > 0) {
			try {
				Thread.sleep(rand.nextInt(4000)+6000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Customer currentInLine = customers.remove();
			myTotal += currentInLine.pay();
			synchronized(balance) {
				balance.update();
				balance.sort();
				System.out.println(balance);
			}
			System.out.println("Kasse" + id + " Hat Kunden " + currentInLine +" bedient. " + getQueueLength() + " Kunden �brig.");
		}
		System.out.println("Kasse " + id + " schlie�t.");
		isOpen = false;
	}

	public int getId() {
		return id;
	}

	public int compareTo(Cashpoint o) {
		return o.myTotal - this.myTotal;
	}

	public String toString(){
		String status = isOpen?"(offen)":"(geschlossen)";
		return "Kasse " + getId() + " " + status +" hat " + myTotal + "€ eingenommen.";
	}

	public int getMyTotal(){
		return myTotal;
	}

	public void setBalance(Balance b){
		balance = b;
	}
}
