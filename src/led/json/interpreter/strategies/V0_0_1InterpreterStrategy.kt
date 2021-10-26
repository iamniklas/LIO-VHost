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
import java.util.ArrayList
import java.util.stream.Stream

class V0_0_1InterpreterStrategy : IInterpreterStrategy {
    override fun interpretLine(_line: String?): Array<ColorRGB> {
        val colorData = _line!!.split(" ".toRegex()).toTypedArray()
        val colors = ArrayList<ColorRGB>()
        for (i in colorData.indices) {
            val ledData = colorData[i].split("-".toRegex()).toTypedArray()
            val arr = Stream.of(*ledData).mapToInt { s: String -> s.toInt() }.toArray()
            colors.add(ColorRGB(arr[0], arr[1], arr[2]))
        }
        return colors.toTypedArray()
    }

    override fun interpretJson(_json: String?): LEDJsonProcedure? {
        val jsonProc = Gson().fromJson(_json, LEDJsonProcedure001Uncompiled::class.java)
        val result = LEDJsonProcedure()
        result.mMetaInfo = jsonProc.mMetaInfo
        val l: MutableList<LEDStateArray> = ArrayList()
        for (i in 0..299) {
            l.add(LEDStateArray())
            l[i].mLEDState = interpretLine(jsonProc.mLEDStates[i])
        }
        result.mLEDStates = arrayOfNulls(300)
        println(result.mLEDStates.size)
        return result
    }
}