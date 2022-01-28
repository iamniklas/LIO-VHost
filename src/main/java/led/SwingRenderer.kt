package led

import javax.swing.JLabel
import com.github.iamniklas.liocore.led.LEDRenderer
import com.github.iamniklas.liocore.led.colorspace.LIOColor
import java.awt.Color
import java.util.ArrayList

class SwingRenderer(private val leds: Array<JLabel?>) : LEDRenderer() {
    override fun render(_colorData: ArrayList<LIOColor>?) {
        for (i in leds.indices) {
            leds[i]!!.foreground = Color(_colorData!![i].r, _colorData[i].g, _colorData[i].b)
        }
    }
}