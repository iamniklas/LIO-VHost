package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.LEDStripManager
import com.github.iamniklas.liocore.led.colorspace.ColorRGB
import com.github.iamniklas.liocore.procedures.Procedure
import java.awt.Color
import java.awt.geom.Area
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SimpleBPMProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mAreas = ArrayList<Area>()
    var mBpm = 60
    var mBeatStep = 0.0f
    var mCounter = 0.0f
    override fun start() {}
    public override fun update() {
        mCounter = if (mCounter > mBeatStep) 0.0f else mCounter + 20.0f
        strip!!.setArea(0, LEDStripManager.LED_COUNT, ColorRGB.black.toSystemColor())
        if (mCounter == 0.0f) {
            val dtf = DateTimeFormatter.ofPattern("mm:ss:SSS")
            val now = LocalDateTime.now()
            println(dtf.format(now))
            strip!!.setArea(0, LEDStripManager.LED_COUNT, ColorRGB.red.toSystemColor())
        }
    }

    init {
        mBeatStep = 60.0f / mBpm.toFloat() * 1000.0f
        println("BEATSTEP: $mBeatStep")
    }
}