package network

import java.io.DataInputStream
import java.io.IOException
import java.io.InputStream
import java.net.Socket
import java.nio.charset.StandardCharsets

class Receiver(var mSocket: Socket, var mInputStream: InputStream, _callback: ReceiveCallback) : Thread() {
    var mDataInputStream: DataInputStream
    var mCallback: ReceiveCallback

    enum class ClientType {
        UNSPECIFIED, CS, CPP, Java
    }

    var mClientType = ClientType.UNSPECIFIED
    @Synchronized
    override fun run() {
        while (!mSocket.isClosed) {
            try {
                var stringBuffer = ""
                var incomingSize = 0
                when (mClientType) {
                    ClientType.CS -> {
                        incomingSize = mDataInputStream.readByte().toInt()
                        stringBuffer = readString(incomingSize)
                        mCallback.onReceiveMessage(stringBuffer)
                    }
                    ClientType.Java -> {
                        val request = mDataInputStream.readUTF()
                        mCallback.onReceiveMessage(request)
                    }
                    ClientType.CPP -> {
                        incomingSize = mDataInputStream.readByte().toInt()
                        stringBuffer = readString(incomingSize)
                        mCallback.onReceiveMessage(stringBuffer)
                    }
                    ClientType.UNSPECIFIED -> {
                        incomingSize = mDataInputStream.readByte().toInt()
                        mClientType = ClientType.values()[incomingSize]
                    }
                }
            } catch (e: IOException) {
                try {
                    //System.out.println("An error occured while receiving incoming data. Type: " + e.getClass().toString());
                    mSocket.close()
                } catch (e1: IOException) {
                    //System.out.println("An error occured while closing the socket. Type: " + e1.getClass().toString());
                }
            }
        }
    }

    @Throws(IOException::class)
    fun readString(_size: Int): String {
        val messageBinary = ByteArray(_size)
        for (i in messageBinary.indices) {
            messageBinary[i] = mDataInputStream.readByte()
        }
        println("Message received")
        return String(messageBinary, StandardCharsets.UTF_8)
    }

    init {
        mDataInputStream = DataInputStream(mInputStream)
        mCallback = _callback
    }
}