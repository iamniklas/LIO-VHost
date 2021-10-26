package procedures

import led.ColorRGB
import led.LEDDataBundle
import java.awt.Color

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
        mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
    }

    public override fun update() {
        if (mStep == 0) {
            start()
        }
        val d = Math.abs(Math.sin(Math.toRadians(mStep.toDouble())))
        val c = Color(0, (d * 255).toInt(), 0)
        for (i in mLEDIndices.indices) {
            mStrip!!.setPixel(mLEDIndices[i], c)
        }
        mStep += 5
        if (mStep == mStepsTotal) {
            mStrip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }
}