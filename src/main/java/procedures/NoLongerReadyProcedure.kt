package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.procedures.Procedure
import java.awt.Color

//A signal animation to notify the user that the strip is no longer ready for any reason (internal error, network disconnect, etc.)
class NoLongerReadyProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mTotalSteps = 60
    var mRedLightActive = false
    override fun start() {}
    public override fun update() {
        if (step % 5 == 0) {
            if (mRedLightActive) {
                strip!!.setAllPixels(Color.BLACK)
            } else {
                strip!!.setAllPixels(Color.RED)
            }
            mRedLightActive = !mRedLightActive
        }
        step++
        if (step > mTotalSteps) {
            strip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }
}