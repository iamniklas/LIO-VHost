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
import led.ColorHSV
import java.awt.Color
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

//Fade one given color in, then fade out to black
class FadeInFadeOutProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private val mColorPartModifier = floatArrayOf(1.0f, 1.0f, 1.0f)
    private val mTotalSteps = 180
    override fun start() {}
    public override fun update() {
        val d = Math.abs(Math.sin(Math.toRadians(mStep.toDouble()))).toFloat()
        mStrip!!.setAllPixels(
            Color(
                (mColorPartModifier[0] * d * 255.0f).toInt(),
                (mColorPartModifier[1] * d * 255.0f).toInt(),
                (mColorPartModifier[2] * d * 255.0f).toInt()
            )
        )
        mStep += 5
        if (mStep >= mTotalSteps) {
            mStrip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }

    init {
        val targetColor = _bundle.colorPrimary!!.toSystemColor()
        mColorPartModifier[0] = targetColor.red.toFloat() / 255.0f
        mColorPartModifier[1] = targetColor.green.toFloat() / 255.0f
        mColorPartModifier[2] = targetColor.blue.toFloat() / 255.0f
    }
}