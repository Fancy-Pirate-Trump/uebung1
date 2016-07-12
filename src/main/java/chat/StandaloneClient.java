package chat;

import java.rmi.RemoteException;
import java.util.Scanner;

public class StandaloneClient {
	static Scanner scanner;
	public static void main(String[] args) {
		String name = scanner.nextLine();
		ChatClient client = null;
		try {
			client = new ChatClient(name);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean loggedIn = true;
		while(loggedIn){
			String msg = scanner.nextLine();
			try {
				client.sendToServer(msg);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
