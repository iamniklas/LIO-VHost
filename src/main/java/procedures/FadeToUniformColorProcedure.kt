package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.led.colorspace.ColorRGBA
import com.github.iamniklas.liocore.procedures.Procedure

//Fade every pixel from a base color to a target color
class FadeToUniformColorProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBaseColor: ColorRGB?
    var mTargetColor: ColorRGB?
    var mDuration = 0.0f
    var mCounter = 0
    var mAlphaStep = 0.0f
    private var mAlphaAddValue = 0.0f
    override fun start() {}
    public override fun update() {
        mCounter++
        mAlphaStep += mAlphaAddValue
        val outputColor = ColorRGBA(mBaseColor!!.r, mBaseColor!!.g, mBaseColor!!.b, (mAlphaStep * 255).toInt())
        strip!!.setAllPixels(outputColor.toRGB(mTargetColor!!).toSystemColor())
        if (mCounter > steps) {
            strip!!.setAllPixels(mBaseColor!!.toSystemColor())
            finishProcedure()
        }
    }

    init {
        mTargetColor = _bundle.colorPrimary
        mBaseColor = _bundle.colorSecondary
        mDuration = _bundle.duration!!.toFloat()
        steps = Math.ceil((mDuration / (strip!!.frametime / 1000.0f)).toDouble()).toInt()
        mAlphaAddValue = 1 / steps.toFloat()
    }
}