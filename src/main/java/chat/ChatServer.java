package chat;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import java.rmi.registry.*;

public class ChatServer extends UnicastRemoteObject implements ChatService{
	/**
	 *
	 */
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
			Naming.bind("clients/"+name, Naming.lookup("//localhost/"+name));
			send(name+" hat sich eingeloggt");
		} catch (MalformedURLException | AlreadyBoundException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
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
		for(String name: getUserList()){
			try {
				ClientService c = (ClientService) Naming.lookup("//localhost/clients/"+name);
				c.send(msg);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<String> getUserList() throws RemoteException {
		try {
			return Arrays.asList(Naming.list("//localhost/clients/"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
