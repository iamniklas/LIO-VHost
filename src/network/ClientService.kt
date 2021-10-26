package network

import java.net.Socket
import java.lang.Thread
import network.ReceiveCallback
import network.Sender
import network.ClientService
import java.io.IOException
import com.google.gson.Gson
import led.LEDStripManager
import com.google.gson.annotations.SerializedName
import procedures.ProcedureType
import led.LEDDataBundle
import network.Receiver.ClientType
import kotlin.jvm.Synchronized
import kotlin.Throws
import java.net.ServerSocket

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