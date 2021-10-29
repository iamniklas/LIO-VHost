package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.procedures.Procedure
import java.awt.Color

//Fade one given color in, then fade out to black
class FadeInFadeOutProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private val mColorPartModifier = floatArrayOf(1.0f, 1.0f, 1.0f)
    private val mTotalSteps = 180
    override fun start() {}
    public override fun update() {
        val d = Math.abs(Math.sin(Math.toRadians(step.toDouble()))).toFloat()
        strip!!.setAllPixels(
            Color(
                (mColorPartModifier[0] * d * 255.0f).toInt(),
                (mColorPartModifier[1] * d * 255.0f).toInt(),
                (mColorPartModifier[2] * d * 255.0f).toInt()
            )
        )
        step += 5
        if (step >= mTotalSteps) {
            strip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }

    init {
        val targetColor = _bundle.colorPrimary!!.toSystemColor()
        mColorPartModifier[0] = targetColor.red.toFloat() / 255.0f
        mColorPartModifier[1] = targetColor.green.toFloat() / 255.0f
        mColorPartModifier[2] = targetColor.blue.toFloat() / 255.0f
    }
}