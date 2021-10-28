package procedures

import led.ColorRGB
import led.LEDDataBundle
import java.awt.Color

class BlinkProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBlinkColor: Color
    var mFrames = 10
    override var mModulo = 2
    override fun start() {}
    public override fun update() {
        mStep++
        if (mStep % mModulo == 0) {
            mStrip!!.setAllPixels(mBlinkColor)
        } else {
            mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
        }
        if (mStep == mSteps) {
            mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
            finishProcedure()
        }
    }

    init {
        mBlinkColor = _bundle.colorPrimary!!.toSystemColor()
        mFrames = _bundle.duration!!
        mModulo = _bundle.modulo!!
        mSteps = mFrames
    }
}