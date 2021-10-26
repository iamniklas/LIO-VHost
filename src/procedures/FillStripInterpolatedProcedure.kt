package procedures

import interpolation.Interpolation.getInterpolationValue
import interpolation.InterpolationType
import led.LEDDataBundle
import led.LEDStripManager
import java.awt.Color

class FillStripInterpolatedProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mLitLEDs = 0
    private var mPpercentage = 0.0f
    private var mFillColor = Color.BLACK
    var mInterpolationType = InterpolationType.EaseOutBounce
    override fun start() {}
    public override fun update() {
        mPpercentage = mStep / LEDStripManager.LED_COUNT.toFloat()
        mStep++
        mLitLEDs = Math.min(300, (getInterpolationValue(mPpercentage, mInterpolationType) * 300.0f).toInt())
        println(mLitLEDs)
        mStrip!!.setAllPixels(Color.BLACK)
        mStrip!!.setArea(0, mLitLEDs, mFillColor)
        if (mStep > LEDStripManager.LED_COUNT) {
            mStrip!!.mProcContainer.removeCurrentProcedure()
            finishProcedure()
        }
    }

    init {
        mFillColor = _bundle.colorPrimary!!.toSystemColor()
    }
}