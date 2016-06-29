package gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ModelCustomer {

	public void startDateService() {
		while (true) {
			askForTimeFromServer();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void askForTimeFromServer() {

		try (Socket serverCon = new Socket("localhost", 6667);
				InputStream in = serverCon.getInputStream();
				OutputStream out = serverCon.getOutputStream()) {
			byte[] dateBytes = new byte[1337];
			in.read(dateBytes);
			String date = new String(dateBytes, "UTF-8");
			System.out.println(date);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
