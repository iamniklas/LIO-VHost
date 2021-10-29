package led.json

import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.google.gson.annotations.SerializedName

class LEDStateArray {
    @SerializedName("led_state")
    lateinit var mLEDState: Array<ColorRGB>
}