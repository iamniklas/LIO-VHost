package procedures

import com.github.iamniklas.liocore.led.*
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.led.colorspace.LIOColor
import com.github.iamniklas.liocore.procedures.*
import java.awt.Color

class BlinkProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBlinkColor: LIOColor
    var mFrames = 10
    var modulo = 2

    override fun start() {}
    override fun update() {
        step++
        if (step % modulo == 0) {
            strip!!.setAllPixels(mBlinkColor)
        } else {
            strip!!.setAllPixels(ColorRGB.black.toSystemColor())
        }
        if (step == steps) {
            strip!!.setAllPixels(ColorRGB.black.toSystemColor())
            finishProcedure()
        }
    }

    init {
        mBlinkColor = _bundle.colorPrimary!!.toSystemColor()
        mFrames = _bundle.duration!!
        modulo = _bundle.modulo!!
        steps = mFrames
    }
}