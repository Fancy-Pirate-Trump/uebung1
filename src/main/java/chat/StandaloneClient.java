package chat;

import java.rmi.RemoteException;
import java.util.Scanner;

public class StandaloneClient {
	static Scanner scanner;
	public static void main(String[] args) {

		ChatClient clinet = null;
		try {
			clinet = new ChatClient("Der_Boii");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			clinet.sendToServer("hi");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
