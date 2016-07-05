package gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class TimeGetter implements Runnable{

	ViewCustomer view;

	public TimeGetter(ViewCustomer vc) {
		view = vc;
	}

	public void run() {
		while(true){
			String time = askForTimeFromServer();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Platform.runLater(()->view.setTime(time));
		}
	}

	public String askForTimeFromServer() {
		String time = "00000";
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName("localhost");
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		// Socket für den Klienten anlegen
		try (DatagramSocket dSocket = new DatagramSocket(5555);) {

			try {

				String command = "time";

				byte buffer[] = null;
				buffer = command.getBytes();

				// Paket mit der Anfrage vorbereiten
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ia, 6667);
				// Paket versenden
				dSocket.send(packet);

				byte answer[] = new byte[1024];
				// Paket für die Antwort vorbereiten
				packet = new DatagramPacket(answer, answer.length);
				// Auf die Antwort warten
				dSocket.receive(packet);

				time = new String(packet.getData(), 0, packet.getLength());

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		return time;
	}
}
