package led.json.interpreter.strategies

import com.google.gson.annotations.SerializedName
import led.json.MetaInfo

class LEDJsonProcedure001Uncompiled {
    @SerializedName("meta")
    var mMetaInfo: MetaInfo? = null

    @SerializedName("led_state")
    lateinit var mLEDStates: Array<String?>
}