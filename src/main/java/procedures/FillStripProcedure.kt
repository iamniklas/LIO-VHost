package procedures

import led.LEDDataBundle
import led.LEDStripManager
import procedures.models.Direction
import java.awt.Color

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
class FillStripProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mFillColor = Color.BLACK
    var mSpeed = 1
    var mDirection: Direction? = Direction.Left
    override fun start() {}
    public override fun update() {
        when (mDirection) {
            Direction.Center -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT / 2 - 1 + mStep + i, mFillColor)
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT / 2 - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            Direction.CenterInvert -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(mStep + i, mFillColor)
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            Direction.Left -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(mStep + i, mFillColor)
                    i++
                }
            }
            Direction.Right -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            else -> {
            }
        }
        mStep += mSpeed
        if (mStep >= mSteps) {
            if (!mIsSubProcedure) {
                finishProcedure()
            }
        }
    }

    init {
        mFillColor = _bundle.colorPrimary!!.toSystemColor()
        mSpeed = Math.round(_bundle.speed!!)
        mDirection = _bundle.direction
        mSteps = LEDStripManager.LED_COUNT
        if (mDirection == Direction.Center || mDirection == Direction.CenterInvert) {
            mSteps /= 2
        }
    }
}