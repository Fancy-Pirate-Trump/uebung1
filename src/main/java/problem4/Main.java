package problem4;

public class Main {

	public static void main(String[] args) {
		WaitingQueue queue = new WaitingQueue();
		Acquisition a = new Acquisition(queue);
		Balance balance = new Balance(queue);
		Cashpoint[] cashpoints = new Cashpoint[7];
		int id = 1;
		for(Cashpoint cp : cashpoints){
			cp = new Cashpoint(id++);
			queue.addCashpoint(cp);
			cp.setTotal(balance);
		}
		a.run();

	}

}
