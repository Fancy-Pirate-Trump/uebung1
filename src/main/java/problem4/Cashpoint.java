package problem4;

import java.util.LinkedList;
import java.util.Random;

public class Cashpoint implements Runnable, Comparable<Cashpoint>{
	private Random rand = new Random();
	private LinkedList<Customer> customers = new LinkedList<>();
	private int id;
	private int balance;
	private Balance total;
	private boolean isOpen;


	public Cashpoint(int id, WaitingQueue q) {
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
			isOpen = true; //Die Kasse Ist vor dem Warten schon offen, damit sich Kunden anstellen können
			Thread.sleep(6000);  //Das öffnen einer Kasse dauert 6 Sekunden
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("Kasse " + id + " geöffnet.");
		while(customers.size() > 0) {
			try {
				Thread.sleep(rand.nextInt(4000)+6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Customer currentInLine = customers.remove();
			balance += currentInLine.pay();
			synchronized(total) {
				System.out.println(total);
			}
			System.out.println("Kasse" + id + " Hat Kunden " + currentInLine +" bedient. " + getQueueLength() + " Kunden übrig.");
		}
		System.out.println("Kasse " + id + " schließt.");
		isOpen = false;
	}

	public int getId() {
		return id;
	}

	public int compareTo(Cashpoint o) {
		return o.balance - this.balance;
	}

	public String toString(){
		String status = isOpen?"(offen)":"(geschlossen)";
		return "Kasse " + getId() + " " + status +" hat " + balance + "€ eingenommen.";
	}

	public int getBalance(){
		return balance;
	}

	public void setTotal(Balance b){
		total = b;
	}
}
