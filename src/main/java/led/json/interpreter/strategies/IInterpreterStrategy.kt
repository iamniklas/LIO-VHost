package led.json.interpreter.strategies

import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import led.json.LEDJsonProcedure

interface IInterpreterStrategy {
    fun interpretLine(_line: String?): Array<ColorRGB>
    fun interpretJson(_json: String?): LEDJsonProcedure?
}