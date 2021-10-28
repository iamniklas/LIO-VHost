package led

import com.google.gson.annotations.SerializedName

class LEDStripConfig {
    @SerializedName("led_count")
    var mLEDCount = 300

    @SerializedName("gpio_pin")
    var mGpioPin = 18

    @SerializedName("freq")
    var mFrequency = 800000

    @SerializedName("dma")
    var mDma = 10

    @SerializedName("brightness")
    var mBrightness = 255

    @SerializedName("pwm_channel")
    var mPwmChannel = 18

    @SerializedName("invert")
    var mInvert = false

    @SerializedName("clear_on_exit")
    var mClearOnExit = true
}