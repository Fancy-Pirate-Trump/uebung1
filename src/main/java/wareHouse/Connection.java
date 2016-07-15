package wareHouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {
	Socket client;
	InputStream input;
	OutputStream output;
	Thread outgoing;
	Thread incoming;

	public Connection(Socket client) {
		try {
			input = client.getInputStream();
			output = client.getOutputStream();
			incoming = new Thread(new Incoming(input));
			incoming.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
