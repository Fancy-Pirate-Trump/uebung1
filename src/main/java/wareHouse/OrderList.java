package wareHouse;

import java.util.ArrayList;

import application.Order;

public class OrderList extends ArrayList<Order>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public double getSum(){
		double sum = 0;
		for(Order o:this){
			sum+=o.getSum();
		}
		return sum;
	}

	public String toString() {
		String s = "----------------------------------------------------------------------\nAlle Ordern:";
		for(Order o:this) {
			s+=o;
		}
		s+="Gesamtsumme: "+getSum()+"\n";
		s+="----------------------------------------------------------------------";
		return s;
	}

}
