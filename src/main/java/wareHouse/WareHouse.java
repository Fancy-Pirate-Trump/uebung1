package wareHouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import application.Order;

public class WareHouse {

	public static void main(String[] args) {

		// ServerSocket erstellen
		try (ServerSocket server = new ServerSocket(6666)) {

			// Timeout nach 1 Minute
			// server.setSoTimeout(60000);
			while (true) {
				try (Socket client = server.accept();
						InputStream in = client.getInputStream();
						OutputStream out = client.getOutputStream()) {

					// Streams erstellen
					ObjectInputStream oi = new ObjectInputStream(in);
					Order order = (Order) oi.readObject();
					out.flush();
					System.out.println(order);

				} catch (IOException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}
}
