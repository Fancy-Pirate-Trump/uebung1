package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends Remote {
	public void login(String name) throws RemoteException;
	public void logout(String name)throws RemoteException;
	public void send(String msg)throws RemoteException;
	public List<String> getUserList()throws RemoteException;
}
