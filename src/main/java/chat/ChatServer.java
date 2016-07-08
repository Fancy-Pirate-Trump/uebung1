package chat;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.buffer.CircularFifoBuffer;

public class ChatServer implements ChatService{
	ArrayList<ClientService> connectedClients;
	CircularFifoBuffer messages = new CircularFifoBuffer(1000);

	@Override
	public void login(String name) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void logout(String name) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(String msg) throws RemoteException {
		messages.add(msg);

	}

	@Override
	public List<String> getUserList() throws RemoteException {
		ArrayList<String> names = new ArrayList<String>();
		for(ClientService client: connectedClients) {
			names.add(client.getName());
		}
		return names;
	}

}
