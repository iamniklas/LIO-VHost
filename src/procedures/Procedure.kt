package procedures

import led.LEDDataBundle
import procedures.Procedure
import led.ColorRGB
import led.ColorRGBA
import interpolation.InterpolationType
import led.LEDStripManager
import interpolation.Interpolation
import javax.script.ScriptEngine
import javax.script.Invocable
import java.lang.NoSuchMethodException
import javax.script.ScriptException
import javax.script.ScriptEngineManager
import led.json.LEDJsonProcedure
import java.nio.file.Files
import java.nio.file.Paths
import led.json.interpreter.LEDInterpreter
import led.json.interpreter.FileVersions
import java.io.IOException
import procedures.models.navigation.PositionMarker
import procedures.ProcedureCalls
import procedures.ProcedureType
import procedures.BootCompleteProcedure
import procedures.ColorInstantSetProcedure
import procedures.FadeInFadeOutProcedure
import procedures.FadeToMultiColorProcedure
import procedures.FadeToUniformColorProcedure
import procedures.FillStripProcedure
import procedures.FillStripInterpolatedProcedure
import procedures.RainbowProcedure
import procedures.RainbowMonoProcedure
import procedures.SimpleBPMProcedure
import procedures.MultiProcedure
import procedures.models.IndeterminateState
import led.ColorHSV
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

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