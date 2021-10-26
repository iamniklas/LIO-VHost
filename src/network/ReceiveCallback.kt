package network

interface ReceiveCallback {
    fun onReceiveMessage(_message: String?)
}