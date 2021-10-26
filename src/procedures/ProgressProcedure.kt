package procedures

import led.ColorRGB
import led.LEDDataBundle
import led.LEDStripManager
import procedures.models.IndeterminateState

class ProgressProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mStart = 0.0f
    private var mProgress = 0.0f
    private val mColor: ColorRGB?
    private var mPulsing = false
    private var mIndeterminate = false
    private var mIndeterminateState = IndeterminateState.Fill
    private var mSineX = 0.0f
    private var mSpeed = 0.2f
    override fun start() {
        mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
    }

    public override fun update() {
        mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
        if (mPulsing) {
            mSineX += mSpeed
            if (mSineX > Math.PI * 2) mSineX = 0f
            mStrip!!.setArea(
                (LEDStripManager.LED_COUNT * mStart).toInt(),
                (LEDStripManager.LED_COUNT * mProgress).toInt(),
                mColor!!.dim(Math.abs(Math.sin(mSineX.toDouble())).toFloat()).toSystemColor()
            )
        } else if (mIndeterminate) {
            when (mIndeterminateState) {
                IndeterminateState.Clear -> {
                    mStart += mSpeed
                    if (mStart >= 1.0f) {
                        mIndeterminateState = IndeterminateState.ClearDone
                    }
                }
                IndeterminateState.ClearDone -> {
                    mStart = 0.0f
                    mProgress = 0.0f
                    mIndeterminateState = IndeterminateState.Fill
                }
                IndeterminateState.Fill -> {
                    mProgress += mSpeed
                    if (mProgress >= 1.0f) {
                        mIndeterminateState = IndeterminateState.FillDone
                    }
                }
                IndeterminateState.FillDone -> {
                    mStart = 0.0f
                    mProgress = 1.0f
                    mIndeterminateState = IndeterminateState.Clear
                }
            }
            mStrip!!.setArea(
                Math.min(Math.max((LEDStripManager.LED_COUNT * mStart).toInt(), 0), 300),
                Math.min(Math.max((LEDStripManager.LED_COUNT * mProgress).toInt(), 0), 300),
                mColor!!.toSystemColor()
            )
        } else {
            mStrip!!.setArea(
                (LEDStripManager.LED_COUNT * mStart).toInt(),
                (LEDStripManager.LED_COUNT * mProgress).toInt(),
                mColor!!.toSystemColor()
            )
        }
    }

    init {
        mColor = _bundle.colorPrimary
        mStart = _bundle.value1!!
        mProgress = _bundle.value2!!
        mIndeterminate = _bundle.indeterminate!!
        mPulsing = _bundle.pulsating!!
        mSpeed = _bundle.speed!!
        mSteps = 1
        start()
    }
}