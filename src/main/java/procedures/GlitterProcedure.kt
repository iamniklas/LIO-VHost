package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.LEDStripManager
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.procedures.Procedure
import java.util.*
import kotlin.math.abs
import kotlin.math.sin

class GlitterProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBaseColor: ColorRGB?
    var mLedX = ArrayList<Float>()
    var someDumbCounter = 0.0f
    var mSpeed = 0.1f
    var tcount = 0.0f
    override fun start() {
        val random = Random()
        for (i in 0 until LEDStripManager.LED_COUNT) {
            mLedX[i] = (random.nextFloat() * (Math.PI * 2 - 0.0f) + 0.0f).toFloat()
        }
    }

    public override fun update() {
        tcount += mSpeed
        if (tcount > Math.PI * 2) tcount = 0f
        for (i in 0 until LEDStripManager.LED_COUNT) {
            strip!!.setPixel(i, mBaseColor!!.dim(abs(sin((tcount + mLedX[i]).toDouble())).toFloat()).toSystemColor())
        }
    }

    init {
        mBaseColor = _bundle.colorPrimary
        mSpeed = _bundle.speed!!
        for (i in 0 until LEDStripManager.LED_COUNT) {
            mLedX.add(0.0f)
        }
        start()
    }
}