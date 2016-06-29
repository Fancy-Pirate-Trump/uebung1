package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
	
	public static void main(String[] args) {
		

		try (ServerSocket server = new ServerSocket(6667)) {

			while (true) {
				try (Socket client = server.accept();
						InputStream in = client.getInputStream();
						OutputStream out = client.getOutputStream()) {

					out.write(simpleDateFormat.format(new Date()).getBytes());
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

}
