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

class LEDJsonProcedure001Uncompiled {
    @SerializedName("meta")
    var mMetaInfo: MetaInfo? = null

    @SerializedName("led_state")
    lateinit var mLEDStates: Array<String?>
}