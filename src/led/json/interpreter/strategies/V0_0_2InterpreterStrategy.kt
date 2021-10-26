package led.json.interpreter.strategies

import led.ColorRGB
import led.json.LEDJsonProcedure
import com.google.gson.annotations.SerializedName
import led.json.MetaInfo
import led.json.interpreter.strategies.IInterpreterStrategy
import com.google.gson.Gson
import java.util.function.ToIntFunction
import led.json.interpreter.strategies.LEDJsonProcedure001Uncompiled
import led.json.LEDStateArray

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