package procedures

import interpolation.Interpolation.getInterpolationValue
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
import procedures.BlinkProcedure
import procedures.GlitterProcedure
import procedures.NavigationProcedure
import procedures.models.IndeterminateState
import led.ColorHSV
import java.awt.Color
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

class FillStripInterpolatedProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mLitLEDs = 0
    private var mPpercentage = 0.0f
    private var mFillColor = Color.BLACK
    var mInterpolationType = InterpolationType.EaseOutBounce
    override fun start() {}
    public override fun update() {
        mPpercentage = mStep / LEDStripManager.LED_COUNT.toFloat()
        mStep++
        mLitLEDs = Math.min(300, (getInterpolationValue(mPpercentage, mInterpolationType) * 300.0f).toInt())
        println(mLitLEDs)
        mStrip!!.setAllPixels(Color.BLACK)
        mStrip!!.setArea(0, mLitLEDs, mFillColor)
        if (mStep > LEDStripManager.LED_COUNT) {
            mStrip!!.mProcContainer.removeCurrentProcedure()
            finishProcedure()
        }
    }

    init {
        mFillColor = _bundle.colorPrimary!!.toSystemColor()
    }
}