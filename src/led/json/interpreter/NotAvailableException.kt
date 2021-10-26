package led.json.interpreter

import kotlin.Throws
import led.json.interpreter.NotAvailableException
import led.json.interpreter.FileVersions
import led.ColorRGB
import led.json.interpreter.strategies.V0_0_1InterpreterStrategy
import led.json.interpreter.strategies.V0_0_2InterpreterStrategy
import led.json.LEDJsonProcedure
import led.json.interpreter.strategies.V0_0_0InterpreterStrategy
import java.lang.Exception

class NotAvailableException(_message: String?) : Exception(_message) {
    companion object {
        private const val serialVersionUID = 4188525626569432881L
    }
}