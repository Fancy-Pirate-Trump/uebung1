package wareHouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import application.Order;

public class Incoming implements Runnable {

	private ObjectInputStream ois;
	private Order order;
	private InputStream input;

	public Incoming(InputStream input) {
		try {
			ois = new ObjectInputStream(this.input = input);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				order = (Order) ois.readObject();
				for (fpt.com.Product p:order){
					System.out.println(p.getName());
				}
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (login(order)) {
				WareHouse.addOrder(order);
			} else {
				WareHouse.rejectOrder(order);
			}
		}
	}

	public boolean login(Order order) {
		if (order.getName().equals("admin") && order.getPassword().equals("admin"))
			return true;
		return false;
	}

}
