package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeServer {
<<<<<<< HEAD
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
=======
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");

>>>>>>> refs/remotes/origin/wtf
	public static void main(String[] args) {
		try (DatagramSocket socket =  new DatagramSocket(6667);){
			while (true) {


				DatagramPacket packet = new DatagramPacket(new byte[5], 5);
				// Auf Paket warten
				try {
					socket.receive(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			

				// Daten auslesen
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				int len = packet.getLength();
				byte[] data = packet.getData();

				System.out.printf(
						"Anfrage von %s vom Port %d mit der Länge %d:%n%s%n",
						address, port, len, new String(data, 0, len));

				// Nutzdaten in ein Stringobjekt übergeben
				String da = new String(data, 0, len);
				// Kommandos sollen durch : getrennt werden
				try (Scanner sc = new Scanner(da).useDelimiter(":")) {
					// Erstes Kommando filtern
					String keyword = sc.next();
					
					if (keyword.equals("time")) {

						Date dt = new Date();
						byte[] myDate =  dateFormat.format(dt).getBytes();

						// Paket mit neuen Daten (Datum) als Antwort vorbereiten
						packet = new DatagramPacket(myDate, myDate.length,
								address, port);
						// Paket versenden
						socket.send(packet);

					} else {
						byte[] myDate = null;
						myDate = new String("Command unknown").getBytes();

						// Paket mit Information, dass das Schlüsselwort
						// ungültig
						// ist
						// als Antwort vorbereiten
						packet = new DatagramPacket(myDate, myDate.length,
								address, port);
						// Paket versenden
						socket.send(packet);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
