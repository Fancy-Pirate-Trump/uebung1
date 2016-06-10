package problem4;

import java.util.ArrayList;
import java.util.LinkedList;

public class WaitingQueue {
	private LinkedList<Customer> waitingCustomers = new LinkedList<Customer>();
	private ArrayList<Cashpoint> cashpoints = new ArrayList<Cashpoint>();
	private int maxOpenCashpoints = 6;
	private boolean acceptsCustomers = true;

	private Cashpoint getCashpointWithLeastCustomers() {
		Cashpoint minimum = null;
		for(Cashpoint cashpoint : getOpenCashpoints()){
			if(minimum == null){
				minimum = cashpoint;
			}
			else {
				if(cashpoint.getQueueLength() < minimum.getQueueLength()) {
					minimum = cashpoint;
				}
			}
		}
		if(minimum == null) { //keine kasse offen
			minimum = getLowestClosedCashpoint(); //�ffne kasse 1
		}
		return minimum;
	}

	private ArrayList<Cashpoint> getOpenCashpoints(){
		ArrayList<Cashpoint> result = new ArrayList<>();
		for(Cashpoint cashpoint : cashpoints) {
			if(cashpoint.isOpen()){
				result.add(cashpoint);
			}
		}
		return result;
	}

	private ArrayList<Cashpoint> getClosedCashpoints(){
		ArrayList<Cashpoint> result = new ArrayList<>();
		for(Cashpoint cashpoint : cashpoints) {
			if(!cashpoint.isOpen()){
				result.add(cashpoint);
			}
		}
		return result;
	}

	private Cashpoint getLowestClosedCashpoint(){
		Cashpoint minimum = null;
		for(Cashpoint cashpoint : getClosedCashpoints()){
			if(minimum == null){
				minimum = cashpoint;
			}
			else {
				if(cashpoint.getId() < minimum.getId()) {
					minimum = cashpoint;
				}
			}
		}

		if(minimum == null)
			System.out.println("Es gibt keine geschlossenen Kassen");
		return minimum;
	}



	private boolean isAnyCashpointAtCapacity(int n){
		for(Cashpoint cashpoint : getOpenCashpoints()){
			if(cashpoint.getQueueLength() >= n)
				return true;
		}
		//Keine kontrollierte Kasse kratzt an Kapazit�t
		return false;
	}

	private boolean areAllCashpointsOpen(){
		return getOpenCashpoints().size() == cashpoints.size() && cashpoints.size() > 0;
	}

	public void addCashpoint(Cashpoint cp) {
		if(cashpoints.size() < maxOpenCashpoints){
			cashpoints.add(cp);
		} else {
			System.out.println("Es gibt bereits " + maxOpenCashpoints + "Kassen. Es kann keine weitere mehr erzeigt werden.");
		}
	}

	public boolean enqueue(Customer customer) {
		return waitingCustomers.add(customer);
	}

	public int size(){
		return waitingCustomers.size();
	}

	public boolean acceptsCustomers(){
		return acceptsCustomers;
	}

	public ArrayList<Cashpoint> getCashpoints() {
		return cashpoints;
	}

	public boolean dequeue() {
		if((getOpenCashpoints().isEmpty() || isAnyCashpointAtCapacity(6)) && !(areAllCashpointsOpen())){
			//offne eine kasse wenn es keine offene gibt oder eine voll ist aber nicht wenn schon alle offwn und voll sind
			getLowestClosedCashpoint().open();
		}

		if(isAnyCashpointAtCapacity(8)){ //Keine Kunden mehr nehmen, wenn eine Kasse bei 8 ist
			acceptsCustomers = false;
		}

		Customer c = waitingCustomers.remove();
		return getCashpointWithLeastCustomers().enqueue(c);
	}

}
