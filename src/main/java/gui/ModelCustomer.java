package gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import application.Order;

public class ModelCustomer {
	private InputStream in;
	private OutputStream out;
	private Socket serverCon;
	
	public ModelCustomer(){
		try {
			serverCon = new Socket("localhost", 6666);
			in = serverCon.getInputStream();
			out = serverCon.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean login(String name, String password) {
		if (name.equals("admin") && password.equals("admin"))
			return true;
		return false;
	}

	public void buy(Order o) {

		// Verbindungsanfrage/Verbindungsaufbau
		// Streams anlegen
			// Zahlenschreiben schreiben
			try {
				ObjectOutputStream oo = new ObjectOutputStream(out);
				oo.writeObject(o);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
