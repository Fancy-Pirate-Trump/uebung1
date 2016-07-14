package chat;

import java.rmi.RemoteException;
import java.util.Scanner;

public class StandaloneClient {
	static Scanner scanner;
	public static void main(String[] args) {
		if(args.length<2){
			System.out.println("Ungültige eingabe");
		}
		ChatClient clinet = null;
		try {
			clinet = new ChatClient(args[0]);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			clinet.sendToServer(args[1]);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
