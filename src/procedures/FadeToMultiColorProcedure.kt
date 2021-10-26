package procedures

import led.ColorRGB.Companion.fromSystemColor
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
import led.ColorHSV
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

//Action is like FadeToUniformColorProcedure, but individual for every pixel
class FadeToMultiColorProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBaseColor = arrayOfNulls<ColorRGB>(300)
    var mTargetColor: ColorRGB?
    var mSpeed = 0.0f
    var mCounter = 0
    override var mSteps = 0
    var mAlphaStep = FloatArray(300)
    private val mAlphaAddValue = FloatArray(300)
    override fun start() {
        for (i in mBaseColor.indices) {
            mBaseColor[i] = fromSystemColor(mStrip!!.mStripData.getColorPyPixel(i)!!)
        }
    }

    public override fun update() {
        for (i in mAlphaStep.indices) {
            mAlphaStep[i] += mAlphaAddValue[i]
            val outputColor =
                ColorRGBA(mBaseColor[i]!!.r, mBaseColor[i]!!.g, mBaseColor[i]!!.b, 255 - (mAlphaStep[i] * 255).toInt())
            mStrip!!.setPixel(i, outputColor.toRGB(mTargetColor!!).toSystemColor())
        }
        mStep++
        if (mStep > mSteps) {
            finishProcedure()
        }
    }

    init {
        mTargetColor = _bundle.colorPrimary
        println(mTargetColor)
        mSpeed = _bundle.speed!!
        mSteps = Math.ceil((mSpeed / (mStrip!!.frametime / 1000.0f)).toDouble()).toInt()
        for (i in mAlphaAddValue.indices) {
            mAlphaAddValue[i] = 1 / mSteps.toFloat()
        }
        start()

        //System.out.println("Multi-Fade Steps " + mSteps);
    }
}