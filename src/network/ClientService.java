package network;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import led.LEDStripManager;

public class ClientService extends Thread implements ReceiveCallback {
	
	static int mNextId = 0;
	public int mId = 0;
	
	Server mServer;
	Socket mSocket;
	public Sender mSender;
	Receiver mReceiver;
	
	public ClientService(Server _server, Socket _socket) {
		mId = mNextId;
		mNextId++;
		
		mServer = _server;
		mSocket = _socket;
		
		try {
			mSender = new Sender(mSocket.getOutputStream());
			mReceiver = new Receiver(mSocket, mSocket.getInputStream(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mSender.send(new Gson().toJson(LEDStripManager.mLEDStatus));
		
		mReceiver.start();
	}

	@Override
	public void onReceiveMessage(String _message) {
		mServer.receiveMessage(_message);
	}
}