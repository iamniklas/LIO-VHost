package led.json.interpreter

class NotAvailableException(_message: String?) : Exception(_message) {
    companion object {
        private const val serialVersionUID = 4188525626569432881L
    }
}