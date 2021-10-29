package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.procedures.Procedure
import java.awt.Color

//Set every strip's pixel to a uniform color in a instant 1 frame animation
class ColorInstantSetProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mTargetColor: Color
    override fun start() {}
    public override fun update() {
        strip!!.setAllPixels(mTargetColor)
        postUpdate()
        finishProcedure()
    }

    init {
        mTargetColor = _bundle.colorPrimary!!.toSystemColor()
    }
}