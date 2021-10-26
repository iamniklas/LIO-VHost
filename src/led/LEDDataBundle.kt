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
import procedures.models.Direction

class LEDDataBundle {
    @JvmField
    var colorPrimary: ColorRGB? = null
    @JvmField
    var colorSecondary: ColorRGB? = null
    var colorTertiary: ColorRGB? = null
    @JvmField
    var data: String? = null
    @JvmField
    var value1: Float? = null
    @JvmField
    var value2: Float? = null
    var value3: Float? = null
    @JvmField
    var modulo: Int? = null
    var interpolation: InterpolationType? = null
    @JvmField
    var direction: Direction? = null
    var bpm: Int? = null
    @JvmField
    var repetitions: Float? = null
    @JvmField
    var speed: Float? = null
    @JvmField
    var duration: Int? = null
    @JvmField
    var pulsating: Boolean? = null
    @JvmField
    var path: String? = null

    //subBundle
    var isSubProcedure: Boolean? = null
    @JvmField
    var indeterminate: Boolean? = null
    @JvmField
    var puModulo: Int? = null
    @JvmField
    var puModuloInvert: Boolean? = null
    @JvmField
    var ledStrip: LEDStripManager? = null
    @JvmField
    var procedureCalls: ProcedureCalls? = null
}