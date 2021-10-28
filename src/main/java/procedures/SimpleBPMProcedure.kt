package procedures

import led.LEDDataBundle
import led.LEDStripManager
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
        mStrip!!.setArea(0, LEDStripManager.LED_COUNT, Color.BLACK)
        if (mCounter == 0.0f) {
            val dtf = DateTimeFormatter.ofPattern("mm:ss:SSS")
            val now = LocalDateTime.now()
            println(dtf.format(now))
            mStrip!!.setArea(0, LEDStripManager.LED_COUNT, Color.RED)
        }
    }

    init {
        mBeatStep = 60.0f / mBpm.toFloat() * 1000.0f
        println("BEATSTEP: $mBeatStep")
    }
}