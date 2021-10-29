package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.colorspace.ColorHSV
import com.github.iamniklas.liocore.procedures.Procedure

class RainbowMonoProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mColorHSV = ColorHSV(0, 1.0f, 1.0f)
    var mHueCounter = 0
    var mRepetitions = 1f
    var mSpeed = 1f
    override fun start() {}
    public override fun update() {
        mColorHSV.h = (if (mColorHSV.h > 360.0f) 0 else mColorHSV.h + mSpeed).toInt()
        strip!!.setAllPixels(mColorHSV.toRGB().toSystemColor())
    }

    init {
        mSpeed = _bundle.speed!!
    }
}