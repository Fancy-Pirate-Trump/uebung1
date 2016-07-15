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
