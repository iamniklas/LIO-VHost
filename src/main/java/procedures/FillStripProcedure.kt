package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.LEDStripManager
import com.github.iamniklas.liocore.procedures.Procedure
import com.github.iamniklas.liocore.procedures.models.Direction
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
                    strip!!.setPixel(LEDStripManager.LED_COUNT / 2 - 1 + step + i, mFillColor)
                    strip!!.setPixel(LEDStripManager.LED_COUNT / 2 - 1 - step - i, mFillColor)
                    i++
                }
            }
            Direction.CenterInvert -> {
                var i = 0
                while (i < mSpeed) {
                    strip!!.setPixel(step + i, mFillColor)
                    strip!!.setPixel(LEDStripManager.LED_COUNT - 1 - step - i, mFillColor)
                    i++
                }
            }
            Direction.Left -> {
                var i = 0
                while (i < mSpeed) {
                    strip!!.setPixel(step + i, mFillColor)
                    i++
                }
            }
            Direction.Right -> {
                var i = 0
                while (i < mSpeed) {
                    strip!!.setPixel(LEDStripManager.LED_COUNT - 1 - step - i, mFillColor)
                    i++
                }
            }
            else -> {
            }
        }
        step += mSpeed
        if (step >= steps) {
            if (!isSubProcedure) {
                finishProcedure()
            }
        }
    }

    init {
        mFillColor = _bundle.colorPrimary!!.toSystemColor()
        mSpeed = Math.round(_bundle.speed!!)
        mDirection = _bundle.direction
        steps = LEDStripManager.LED_COUNT
        if (mDirection == Direction.Center || mDirection == Direction.CenterInvert) {
            steps /= 2
        }
    }
}