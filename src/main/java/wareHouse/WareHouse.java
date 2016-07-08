package wareHouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import application.Order;

public class WareHouse {
	private ServerSocket server;
	Socket client;
	static InputStream in;
	static OutputStream out;

	public WareHouse() {
		try {
			server = new ServerSocket(6666);
			client = server.accept();
			in = client.getInputStream();
			out = client.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// ServerSocket erstellen
		while (true) {

			try {
				ObjectInputStream oi = new ObjectInputStream(in);
				Order order = (Order) oi.readObject();
				out.flush();
				System.out.println(order);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

