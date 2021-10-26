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

class ColorRGBA(_r: Int, _g: Int, _b: Int, _a: Int) {
    var r = 255
    var g = 255
    var b = 255
    var a = 255
    fun toRGB(): ColorRGB {
        return ColorRGB(
            (255 - a) * ColorRGB.Companion.black.r + a * r,
            (255 - a) * ColorRGB.Companion.black.g + a * g,
            (255 - a) * ColorRGB.Companion.black.b + a * b
        )
    }

    fun toRGB(_baseColor: ColorRGB): ColorRGB {
        val rgb = ColorRGB(
            ((1 - a / 255.0f) * _baseColor.r + a / 255.0f * r).toInt(),
            ((1 - a / 255.0f) * _baseColor.g + a / 255.0f * g).toInt(),
            ((1 - a / 255.0f) * _baseColor.b + a / 255.0f * b).toInt()
        )
        rgb.r = Math.max(0, Math.min(rgb.r, 255))
        rgb.g = Math.max(0, Math.min(rgb.g, 255))
        rgb.b = Math.max(0, Math.min(rgb.b, 255))
        return rgb
    }

    fun dim(_percentage: Float): ColorRGBA {
        return ColorRGBA(
            (r * _percentage).toInt(),
            (g * _percentage).toInt(),
            (b * _percentage).toInt(),
            (a * _percentage).toInt()
        )
    }

    fun dim(_percentageR: Float, _percentageG: Float, _percentageB: Float, _percentageA: Float): ColorRGBA {
        return ColorRGBA(
            (r * _percentageR).toInt(),
            (g * _percentageG).toInt(),
            (b * _percentageB).toInt(),
            (a * _percentageA).toInt()
        )
    }

    override fun toString(): String {
        return String.format("R%d G%d B%d A%d", r, g, b, a)
    }

    init {
        r = _r
        g = _g
        b = _b
        a = _a
    }
}