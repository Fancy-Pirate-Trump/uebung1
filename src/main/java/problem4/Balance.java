package problem4;

import java.util.ArrayList;

public class Balance {
	private int total = 0;
	private WaitingQueue queue;
	ArrayList<Cashpoint> cashpoints;

	public Balance (WaitingQueue q){
		queue = q;
	}

	public void update() {
		cashpoints = (ArrayList<Cashpoint>) queue.getCashpoints().clone();
		total = 0;
		for(Cashpoint cp : cashpoints){
			total += cp.getMyTotal();
		}
	}

	public void sort(){
		cashpoints.sort(null);
	}

	public String toString() {
		String s = "Geordnete Bilanzen:\n";
		for(Cashpoint cp : cashpoints){
			s += cp + "\n";
		}
		s += "Insgesamt: " + total + "�";
		return s;
	}


}
