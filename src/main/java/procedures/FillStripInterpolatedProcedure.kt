package procedures

import com.github.iamniklas.interpolation.Interpolation.getInterpolationValue
import com.github.iamniklas.interpolation.InterpolationType
import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.LEDStripManager
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.procedures.Procedure

class FillStripInterpolatedProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mLitLEDs = 0
    private var mPpercentage = 0.0f
    private var mFillColor = ColorRGB.black.toSystemColor()
    var mInterpolationType = InterpolationType.EaseInOutExpo
    override fun start() {}
    public override fun update() {
        mPpercentage = step / LEDStripManager.LED_COUNT.toFloat()
        step++
        mLitLEDs = Math.min(300, (getInterpolationValue(mPpercentage, mInterpolationType) * 300.0f).toInt())

        //println(mLitLEDs)

        strip!!.setAllPixels(ColorRGB.black.toSystemColor())
        strip!!.setArea(0, mLitLEDs, mFillColor)
        if (step > LEDStripManager.LED_COUNT) {
            strip!!.procContainer.removeCurrentProcedure()
            finishProcedure()
        }
    }

    init {
        mFillColor = _bundle.colorPrimary!!.toSystemColor()
    }
}