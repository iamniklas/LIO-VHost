package led.json

import com.google.gson.annotations.SerializedName
import led.ColorRGB

class LEDStateArray {
    @SerializedName("led_state")
    lateinit var mLEDState: Array<ColorRGB>
}