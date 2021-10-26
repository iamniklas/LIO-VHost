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
import java.util.ArrayList

class Server(private val mPort: Int) : Thread() {
    var mServerSocket: ServerSocket? = null
    var mCallback: ReceiveCallback? = null
    override fun run() {
        try {
            println("Network \tINIT \tSTART")
            mServerSocket = ServerSocket(mPort)
            println("Network \tINIT \tDONE")
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        while (true) {
            try {
                mClients.add(ClientService(this, mServerSocket!!.accept()))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getClient(_id: Int): ClientService {
        return mClients[_id]
    }

    fun setListener(_callback: ReceiveCallback?) {
        mCallback = _callback
    }

    fun receiveMessage(_message: String?) {
        mCallback!!.onReceiveMessage(_message)
    }

    companion object {
        var mClients = ArrayList<ClientService>()
    }
}