package led.json

import com.google.gson.annotations.SerializedName

class LEDJsonProcedure {
    @SerializedName("meta")
    var mMetaInfo: MetaInfo? = null

    @SerializedName("led_state")
    lateinit var mLEDStates: Array<LEDStateArray?>
}