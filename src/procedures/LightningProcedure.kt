package procedures

import led.ColorRGB
import led.LEDDataBundle

class LightningProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mSpeed: Float
    private val mAtenuation: Float
    override fun start() {}
    public override fun update() {
        mStrip!!.setAllPixels(
            ColorRGB.white50.dim(Math.abs(Math.sin((mStep / mSpeed).toDouble())).toFloat()).toSystemColor()
        )
        mSpeed += mAtenuation
        if (mStep > mSteps) {
            finishProcedure(true)
        }
        mStep++
    }

    init {
        mSpeed = _bundle.speed!!
        mAtenuation = _bundle.value1!!
        mSteps = _bundle.duration!! * 50
    }
}