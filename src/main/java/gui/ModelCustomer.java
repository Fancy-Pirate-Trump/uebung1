package gui;

import java.io.IOException;
<<<<<<< HEAD
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
=======
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
>>>>>>> refs/remotes/origin/wtf

public class ModelCustomer {
	private String time;

<<<<<<< HEAD

	public void startDateService() {
=======
	public String startDateService() {
>>>>>>> refs/remotes/origin/wtf
		while (true) {
			time = askForTimeFromServer();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return time;
		}
	}

<<<<<<< HEAD
	private void askForTimeFromServer() {

		try (Socket serverCon = new Socket("localhost", 6667);
				InputStream in = serverCon.getInputStream();
				OutputStream out = serverCon.getOutputStream()) {
			byte[] dateBytes = new byte[1337];
			in.read(dateBytes);
			String date = new String(dateBytes, "UTF-8");
			System.out.println(date);
		} catch (IOException e) {
			System.out.println("Server offline?");
=======
	private String askForTimeFromServer() {

		InetAddress ia = null;
		try {
			ia = InetAddress.getByName("localhost");
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
>>>>>>> refs/remotes/origin/wtf
		}
		// Socket für den Klienten anlegen
		try (DatagramSocket dSocket = new DatagramSocket(5555);) {

			try {
				while (true) {
					String command = "time";

					byte buffer[] = null;
					buffer = command.getBytes();

					// Paket mit der Anfrage vorbereiten
					DatagramPacket packet = new DatagramPacket(buffer,
							buffer.length, ia, 6667);
					// Paket versenden
					dSocket.send(packet);

					byte answer[] = new byte[1024];
					// Paket für die Antwort vorbereiten
					packet = new DatagramPacket(answer, answer.length);
					// Auf die Antwort warten
					dSocket.receive(packet);

					time = new String(packet.getData(), 0, packet
							.getLength());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return time;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		return time;
	}

}

