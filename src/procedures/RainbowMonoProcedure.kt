package procedures

import led.ColorHSV
import led.LEDDataBundle

class RainbowMonoProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mColorHSV = ColorHSV(0, 1.0f, 1.0f)
    var mHueCounter = 0
    var mRepetitions = 1f
    var mSpeed = 1f
    override fun start() {}
    public override fun update() {
        mColorHSV.h = (if (mColorHSV.h > 360.0f) 0 else mColorHSV.h + mSpeed).toInt()
        mStrip!!.setAllPixels(mColorHSV.ToRGB().toSystemColor())
    }

    init {
        mSpeed = _bundle.speed!!
    }
}