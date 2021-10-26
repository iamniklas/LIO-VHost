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
import java.io.DataOutputStream
import java.io.OutputStream
import kotlin.jvm.Synchronized
import kotlin.Throws
import java.net.ServerSocket

class Sender(_outStream: OutputStream?) : Thread() {
    var mOutStream: DataOutputStream
    fun send(_message: String?) {
        try {
            mOutStream.writeUTF(_message)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }
    }

    init {
        mOutStream = DataOutputStream(_outStream)
    }
}