package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread {
	ServerSocket mServerSocket;
	
	static ArrayList<ClientService> mClients = new ArrayList<ClientService>();
	
	private int mPort;
	
	ReceiveCallback mCallback;
	
	public Server(int _port) {
		mPort = _port;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Network \tINIT \tSTART");
			mServerSocket = new ServerSocket(mPort);
			System.out.println("Network \tINIT \tDONE");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true) {
			try {
				mClients.add(new ClientService(this, mServerSocket.accept()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ClientService getClient(int _id) {
		return mClients.get(_id);
	}
	
	public void setListener(ReceiveCallback _callback) {
		mCallback = _callback;
	}
	
	public void receiveMessage(String _message) {
		mCallback.onReceiveMessage(_message);
	}
}
