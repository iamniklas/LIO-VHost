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
import java.awt.Color
import java.util.ArrayList

class LEDStrip(_ledCount: Int) {
    var mStrip = ArrayList<Color?>(300)
    fun getColorPyPixel(_id: Int): Color? {
        return mStrip[_id]
    }

    init {
        mStrip = ArrayList(_ledCount)
        for (i in 0 until LEDStripManager.Companion.LED_COUNT) {
            mStrip.add(Color.BLACK)
        }
    }
}