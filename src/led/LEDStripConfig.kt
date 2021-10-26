package led

import led.ColorRGB
import com.google.gson.annotations.SerializedName
import led.ColorChannel
import led.ColorRGBA
import led.ColorHSV
import interpolation.InterpolationType
import led.LEDStripManager
import procedures.ProcedureCalls
import javax.swing.JLabel
import led.LEDStripConfig
import led.LEDStrip
import procedures.ProcContainer
import led.LEDStripRenderer
import java.lang.InterruptedException
import procedures.Procedure
import led.json.LEDStatus
import network.LEDChangeModel
import led.LEDDataBundle
import procedures.ProcedureType
import procedures.JavascriptProcedure
import procedures.ProcedureFactory

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