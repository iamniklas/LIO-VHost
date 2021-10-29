package led

import com.github.iamniklas.liocore.interpolation.*
import procedures.ProcedureCalls
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