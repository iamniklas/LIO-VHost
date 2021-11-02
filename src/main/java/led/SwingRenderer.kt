package led

import com.github.iamniklas.liocore.led.LEDRenderer
import javax.swing.JLabel

class SwingRenderer(jLabels: Array<JLabel?>) : LEDRenderer(jLabels.size) {
    private val uiLabels = jLabels

    override fun render() {
        for(i in uiLabels.indices) {
            uiLabels[i]!!.foreground = colorData[i]
        }
    }
}