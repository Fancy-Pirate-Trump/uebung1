package gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import application.Order;

public class ModelCustomer {

	public boolean login(String name, String password) {
		if (name.equals("admin") && password.equals("admin"))
			return true;
		return false;
	}

	public void buy(Order o) {

		// Verbindungsanfrage/Verbindungsaufbau
		// Streams anlegen
		try (Socket serverCon = new Socket("localhost", 6666);
				InputStream in = serverCon.getInputStream();
				OutputStream out = serverCon.getOutputStream()) {
			// Zahlenschreiben schreiben
			ObjectOutputStream oo = new ObjectOutputStream(out);
			oo.writeObject(o);
			out.flush();

			// Ergebnis entgegennehmen
			int result = in.read();

			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
