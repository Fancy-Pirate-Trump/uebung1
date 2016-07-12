package wareHouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import application.Order;

public class Incoming implements Runnable{

	public Incoming(InputStream input) {
		try {
			ObjectInputStream ois = new ObjectInputStream(input);
			Order order = (Order) ois.readObject();
			if (login(order)) {
				WareHouse.addOrder(order);
			} else {
				WareHouse.rejectOrder(order);
			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {


	}

	public boolean login(Order order) {
		if (order.getName().equals("admin") && order.getPassword().equals("admin"))
			return true;
		return false;
	}

}
