package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.led.colorspace.ColorRGBA
import com.github.iamniklas.liocore.procedures.Procedure

//Procedure is like FadeToUniformColorProcedure, but individual for every pixel
class FadeToMultiColorProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBaseColor = arrayOfNulls<ColorRGB>(300)
    var mTargetColor: ColorRGB?
    var mSpeed = 0.0f
    var mCounter = 0
    var mAlphaStep = FloatArray(300)
    private val mAlphaAddValue = FloatArray(300)
    override fun start() {
        for (i in mBaseColor.indices) {
            mBaseColor[i] = ColorRGB.fromSystemColor(strip.ledStrip.getColorByPixel(i))
        }
    }

    public override fun update() {
        for (i in mAlphaStep.indices) {
            mAlphaStep[i] += mAlphaAddValue[i]
            val outputColor =
                ColorRGBA(mBaseColor[i]!!.r, mBaseColor[i]!!.g, mBaseColor[i]!!.b, 255 - (mAlphaStep[i] * 255).toInt())
            strip!!.setPixel(i, outputColor.toRGB(mTargetColor!!).toSystemColor())
        }
        step++
        if (step > steps) {
            finishProcedure()
        }
    }

    init {
        mTargetColor = _bundle.colorPrimary
        println(mTargetColor)
        mSpeed = _bundle.speed!!
        steps = Math.ceil((mSpeed / (strip!!.frametime / 1000.0f)).toDouble()).toInt()
        for (i in mAlphaAddValue.indices) {
            mAlphaAddValue[i] = 1 / steps.toFloat()
        }
        start()

        //System.out.println("Multi-Fade Steps " + mSteps);
    }
}