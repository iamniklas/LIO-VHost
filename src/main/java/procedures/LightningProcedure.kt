package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.procedures.Procedure

class LightningProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mSpeed: Float
    private val mAtenuation: Float
    override fun start() {}
    public override fun update() {
        strip!!.setAllPixels(
            ColorRGB.white50.dim(Math.abs(Math.sin((step / mSpeed).toDouble())).toFloat()).toSystemColor()
        )
        mSpeed += mAtenuation
        if (step > steps) {
            finishProcedure(true)
        }
        step++
    }

    init {
        mSpeed = _bundle.speed!!
        mAtenuation = _bundle.value1!!
        steps = _bundle.duration!! * 50
    }
}