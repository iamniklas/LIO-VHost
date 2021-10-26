package network

import com.google.gson.Gson
import led.LEDStripManager
import java.io.IOException
import java.net.Socket

class ClientService(_server: Server, _socket: Socket) : Thread(), ReceiveCallback {
    var mId = 0
    var mServer: Server
    var mSocket: Socket
    var mSender: Sender? = null
    var mReceiver: Receiver? = null
    override fun onReceiveMessage(_message: String?) {
        println("Message received")
        mServer.receiveMessage(_message)
    }

    companion object {
        var mNextId = 0
    }

    init {
        mId = mNextId
        mNextId++
        mServer = _server
        mSocket = _socket
        try {
            mSender = Sender(mSocket.getOutputStream())
            mReceiver = Receiver(mSocket, mSocket.getInputStream(), this)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mSender!!.send(Gson().toJson(LEDStripManager.mLEDStatus))
        mReceiver!!.start()
    }
}