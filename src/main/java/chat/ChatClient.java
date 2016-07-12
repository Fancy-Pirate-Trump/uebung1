package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements ClientService {
	ChatService server;
	String name;
	public ChatClient(String name) throws RemoteException {
		try {
			server = (ChatService) Naming.lookup("localhost/chat");
			this.name = name;
			server.login(name);
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Override
	public void send(String msg){
		System.out.println(msg);

	}

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	public void sendToServer(String msg)  throws RemoteException {
		server.send(msg);
	}

}
