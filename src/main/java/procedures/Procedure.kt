package procedures

import led.ColorRGB
import led.LEDDataBundle
import led.LEDStripManager

abstract class Procedure(_bundle: LEDDataBundle) {
    var mStrip: LEDStripManager?
    var mCallbacks: ProcedureCalls?
    var mStep = 0
    open var mSteps = 0
    open var mModulo = 1
    var mModuloInvert = false
    protected var mIsSubProcedure = false
    abstract fun start()
    abstract fun update()
    fun postUpdate() {
        if (mModulo < 2) return
        for (i in 0 until LEDStripManager.LED_COUNT) {
            if (mModuloInvert) {
                if (i % mModulo != 0) {
                    mStrip!!.setPixel(i, ColorRGB.black.toSystemColor())
                }
            } else {
                if (i % mModulo == 0) {
                    mStrip!!.setPixel(i, ColorRGB.black.toSystemColor())
                }
            }
        }
        if (mStep > mSteps) {
            finishProcedure()
        }
    }

    protected fun finishProcedure(_clearStrip: Boolean) {
        mStrip!!.mProcContainer.removeCurrentProcedure()
        if (_clearStrip) {
            mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
        }
        mCallbacks!!.onProcedureFinish()
    }

    protected fun finishProcedure() {
        mStrip!!.mProcContainer.removeCurrentProcedure()
        mCallbacks!!.onProcedureFinish()
    }

    init {
        mStrip = _bundle.ledStrip
        mCallbacks = _bundle.procedureCalls
        if (_bundle.puModulo != null) {
            mModulo = _bundle.puModulo!!
        }
        if (_bundle.puModuloInvert != null) {
            mModuloInvert = _bundle.puModuloInvert!!
        }
    }
}