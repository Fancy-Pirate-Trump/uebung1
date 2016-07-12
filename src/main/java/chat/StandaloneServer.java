package chat;

import java.rmi.RemoteException;

public class StandaloneServer {
	public static void main(String[] args) {
		try {
			new ChatServer();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Chatserver gestartet!");
	}
}
