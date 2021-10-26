package procedures

import led.LEDDataBundle
import java.awt.Color

//A signal animation to notify the user that the strip is no longer ready for any reason (internal error, network disconnect, etc.)
class NoLongerReadyProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mTotalSteps = 60
    var mRedLightActive = false
    override fun start() {}
    public override fun update() {
        if (mStep % 5 == 0) {
            if (mRedLightActive) {
                mStrip!!.setAllPixels(Color.BLACK)
            } else {
                mStrip!!.setAllPixels(Color.RED)
            }
            mRedLightActive = !mRedLightActive
        }
        mStep++
        if (mStep > mTotalSteps) {
            mStrip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }
}