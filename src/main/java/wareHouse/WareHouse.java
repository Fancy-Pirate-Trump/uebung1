package wareHouse;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import application.Order;

public class WareHouse {
	static private ServerSocket server;
	static OrderList orderList = new OrderList();



	public static void main(String[] args) {
		try {
			server = new ServerSocket(6666);
			while(true) {
				new Connection(server.accept());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	public static void addOrder(Order order) {
		synchronized(orderList){
			orderList.add(order);
		}
		System.out.println("Order eingegangen: \n");
		System.out.println(order);
		System.out.print(orderList);

	}




	public static void rejectOrder(Order order) {
		System.out.println("Order von "+order.getName()+" abgelehnt: Keine Berechtigung!");

	}


}

