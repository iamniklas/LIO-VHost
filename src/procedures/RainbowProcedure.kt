package procedures

import led.LEDDataBundle
import led.LEDStripManager
import led.ColorHSV
import procedures.models.Direction

class RainbowProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mColorHSV = ColorHSV(0, 1.0f, 1.0f)
    private var mHueCounter = 0f
    private val mHueArrayCounter = FloatArray(300)
    private var mRepetitions = 0.75f
    private var mSpeed = 3f
    private var mDirection: Direction? = Direction.Left
    override fun start() {}
    public override fun update() {
        when (mDirection) {
            Direction.Center -> {
                var i = 0
                while (i < mHueArrayCounter.size) {
                    mHueArrayCounter[i] = if (mHueArrayCounter[i] < 0) 360.0f else mHueArrayCounter[i] - mSpeed
                    i++
                }
            }
            Direction.CenterInvert -> {
                var i = 0
                while (i < mHueArrayCounter.size) {
                    mHueArrayCounter[i] = if (mHueArrayCounter[i] > 360) 0.0f else mHueArrayCounter[i] + mSpeed
                    i++
                }
            }
            Direction.Left -> mHueCounter = if (mHueCounter > 360) 0.0f else mHueCounter + mSpeed
            Direction.Right -> mHueCounter = if (mHueCounter < 0) 360.0f else mHueCounter - mSpeed
        }
        for (i in 0 until LEDStripManager.LED_COUNT) {
            if (mDirection == Direction.Center || mDirection == Direction.CenterInvert) {
                mColorHSV.h =
                    ((i * (mRepetitions * (360.0f / LEDStripManager.LED_COUNT.toFloat()))).toInt() + mHueArrayCounter[i]).toInt() % 360
                mStrip!!.setPixel(i, mColorHSV.ToRGB().toSystemColor())
                continue
            }
            mColorHSV.h =
                ((i * (mRepetitions * (360.0f / LEDStripManager.LED_COUNT.toFloat()))).toInt() + mHueCounter).toInt() % 360
            mStrip!!.setPixel(i, mColorHSV.ToRGB().toSystemColor())
        }
    }

    init {
        mRepetitions = _bundle.repetitions!!
        mSpeed = _bundle.speed!!
        mDirection = _bundle.direction
        for (i in mHueArrayCounter.indices) {
            mHueArrayCounter[i] = Math.abs(i - 150).toFloat()
        }
    }
}