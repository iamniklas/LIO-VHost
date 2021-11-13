package led

import javax.swing.JLabel
import com.github.iamniklas.liocore.led.LEDRenderer
import java.awt.Color

class SwingRenderer(private val leds: Array<JLabel>, _stripSize: Int) : LEDRenderer(_stripSize) {
    override fun render() {
        for (i in leds.indices) {
            leds[i].foreground = Color(colorData[i].r, colorData[i].g, colorData[i].b)
        }
    }
}