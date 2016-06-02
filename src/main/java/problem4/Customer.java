package problem4;

import java.util.Random;

public class Customer {
	private int money;
	private String name;


	public Customer(){
		Random rand = new Random();
		money = rand.nextInt(100);
		name = "Robert Paulson";
	}

	public int pay(){
		int m = money;
		money = 0;
		return m;
	}

	public String toString() {
		return name;
	}
}
