package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.procedures.Procedure
import java.awt.Color
import kotlin.math.abs
import kotlin.math.sin

//A signal animation for the user that the strip is ready for use
class BootCompleteProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mStepsTotal = 720
    var mLEDIndices = intArrayOf(
        0, 10, 20, 30, 40, 50, 60, 70,
        80, 90, 100, 110, 120, 130, 140, 150,
        160, 170, 180, 190, 200, 210, 220, 230,
        240, 250, 260, 270, 280, 290, 299
    )

    override fun start() {
        strip!!.setAllPixels(ColorRGB.black.toSystemColor())
    }

    public override fun update() {
        if (step == 0) {
            start()
        }
        val d = abs(sin(Math.toRadians(step.toDouble())))
        val c = Color(0, (d * 255).toInt(), 0)
        for (i in mLEDIndices.indices) {
            strip!!.setPixel(mLEDIndices[i], c)
        }
        step += 5
        if (step == mStepsTotal) {
            strip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }
}