package procedures

import com.github.iamniklas.liocore.interpolation.*
import com.github.iamniklas.liocore.interpolation.Interpolation.*
import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.LEDStripManager
import com.github.iamniklas.liocore.procedures.Procedure
import java.awt.Color

class FillStripInterpolatedProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mLitLEDs = 0
    private var mPpercentage = 0.0f
    private var mFillColor = Color.BLACK
    var mInterpolationType = InterpolationType.EaseInOutExpo
    override fun start() {}
    public override fun update() {
        mPpercentage = step / LEDStripManager.LED_COUNT.toFloat()
        step++
        mLitLEDs = Math.min(300, (getInterpolationValue(mPpercentage, mInterpolationType) * 300.0f).toInt())

        //println(mLitLEDs)

        strip!!.setAllPixels(Color.BLACK)
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