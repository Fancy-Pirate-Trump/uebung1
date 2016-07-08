package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
	public void send(String msg) throws RemoteException;
	public String getName() throws RemoteException;
}
