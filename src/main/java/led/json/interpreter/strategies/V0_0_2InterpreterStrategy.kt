package led.json.interpreter.strategies

import led.ColorRGB
import led.json.LEDJsonProcedure

class V0_0_2InterpreterStrategy : IInterpreterStrategy {
    override fun interpretLine(_line: String?): Array<ColorRGB> {
        // TODO Auto-generated method stub
        return arrayOf()
    }

    override fun interpretJson(_json: String?): LEDJsonProcedure? {
        // TODO Auto-generated method stub
        return null
    }
}