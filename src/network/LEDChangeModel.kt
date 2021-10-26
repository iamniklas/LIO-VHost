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

class LEDChangeModel {
    @SerializedName(value = "procedure")
    var mProcedure: ProcedureType? = null

    @SerializedName(value = "bundle")
    var mBundle: LEDDataBundle? = null
}