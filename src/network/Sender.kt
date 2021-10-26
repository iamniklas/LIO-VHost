package network

import java.io.DataOutputStream
import java.io.IOException
import java.io.OutputStream

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