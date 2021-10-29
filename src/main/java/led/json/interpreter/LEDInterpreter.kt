package led.json.interpreter

import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import led.json.LEDJsonProcedure
import led.json.interpreter.strategies.V0_0_0InterpreterStrategy
import led.json.interpreter.strategies.V0_0_1InterpreterStrategy
import led.json.interpreter.strategies.V0_0_2InterpreterStrategy

object LEDInterpreter {
    @Throws(NotAvailableException::class)
    fun interpretLine(_fileVersion: FileVersions?, _line: String?): Array<ColorRGB>? {
        return when (_fileVersion) {
            FileVersions.V0_0_0 -> throw NotAvailableException("V0_0_0Interpreter cannot interpret lines")
            FileVersions.V0_0_1 -> V0_0_1InterpreterStrategy().interpretLine(_line)
            FileVersions.V0_0_2 -> V0_0_2InterpreterStrategy().interpretLine(_line)
            else -> null
        }
    }

    fun interpretJson(_fileVersion: FileVersions?, _json: String?): LEDJsonProcedure? {
        return when (_fileVersion) {
            FileVersions.V0_0_0 -> V0_0_0InterpreterStrategy().interpretJson(_json)
            FileVersions.V0_0_1 -> V0_0_1InterpreterStrategy().interpretJson(_json)
            FileVersions.V0_0_2 -> V0_0_2InterpreterStrategy().interpretJson(_json)
            else -> null
        }
    }
}