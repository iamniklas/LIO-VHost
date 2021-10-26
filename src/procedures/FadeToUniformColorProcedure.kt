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
import procedures.BlinkProcedure
import procedures.GlitterProcedure
import procedures.NavigationProcedure
import procedures.ProgressProcedure
import procedures.LightningProcedure
import procedures.NoLongerReadyProcedure
import procedures.JsonProcedure
import procedures.models.IndeterminateState
import led.ColorHSV
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

//Fade every pixel from a base color to a target color
class FadeToUniformColorProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBaseColor: ColorRGB?
    var mTargetColor: ColorRGB?
    var mDuration = 0.0f
    var mCounter = 0
    override var mSteps = 0
    var mAlphaStep = 0.0f
    private var mAlphaAddValue = 0.0f
    override fun start() {}
    public override fun update() {
        mCounter++
        mAlphaStep += mAlphaAddValue
        val outputColor = ColorRGBA(mBaseColor!!.r, mBaseColor!!.g, mBaseColor!!.b, (mAlphaStep * 255).toInt())
        mStrip!!.setAllPixels(outputColor.toRGB(mTargetColor!!).toSystemColor())
        if (mCounter > mSteps) {
            mStrip!!.setAllPixels(mBaseColor!!.toSystemColor())
            finishProcedure()
        }
    }

    init {
        mTargetColor = _bundle.colorPrimary
        mBaseColor = _bundle.colorSecondary
        mDuration = _bundle.duration!!.toFloat()
        mSteps = Math.ceil((mDuration / (mStrip!!.frametime / 1000.0f)).toDouble()).toInt()
        mAlphaAddValue = 1 / mSteps.toFloat()
    }
}