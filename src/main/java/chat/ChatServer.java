package chat;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ChatServer extends UnicastRemoteObject implements ChatService{
	private final String clientAddress = "//localhost/";
	private final String clientPath = "clients/";

	private static final long serialVersionUID = -4770948517004604445L;

	public ChatServer() throws RemoteException{
		LocateRegistry.createRegistry(1099);
		try {
			Naming.rebind("chat", this);
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void login(String name) throws RemoteException {
		try {
			Remote client = Naming.lookup(clientAddress+name);
			Naming.bind(clientPath+name, client);
			send(name+" hat sich eingeloggt");
		} catch (AlreadyBoundException e) {
			send("Warnung: Name "+name+" ist bereits belegt");
			e.printStackTrace();
		} catch (NotBoundException e) {
			send("Remote Object "+name+" nicht auf client gefunden");
			e.printStackTrace();
		} catch (MalformedURLException e) {

		}

	}

	@Override
	public void logout(String name) throws RemoteException {
		try {
			Naming.unbind(name);
		} catch (MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
		send(name+" hat sich ausgeloggt");
	}

	@Override
	public void send(String msg) throws RemoteException {
		List<String> userList = getUserList();
		for(String user: userList){
			try {
				ClientService c = (ClientService) Naming.lookup(user);
				c.send(msg);
				System.out.println("Schicke nachricht an "+user);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				System.out.println(user+" Nicht gefunden\n");
				continue;
			}
		}
		System.out.println(msg+"\n");

	}

	@Override
	public List<String> getUserList() throws RemoteException {
		List<String> registeredNames = null;
		ArrayList<String> clients = new ArrayList<String>();
		try {
			registeredNames = Arrays.asList(Naming.list("//localhost/"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


		for(String path:registeredNames){
			if(path.startsWith("//localhost:1099/clients/"))
				clients.add(path);
		}

		return clients;
	}

}
