package led.json.interpreter.strategies

import com.google.gson.Gson
import led.ColorRGB
import led.json.LEDJsonProcedure

class V0_0_0InterpreterStrategy : IInterpreterStrategy {
    override fun interpretLine(_line: String?): Array<ColorRGB> {
        return arrayOf()
    }

    override fun interpretJson(_json: String?): LEDJsonProcedure? {
        return Gson().fromJson(_json, LEDJsonProcedure::class.java)
    }
}