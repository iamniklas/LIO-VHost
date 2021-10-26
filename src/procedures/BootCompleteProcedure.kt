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
import procedures.models.IndeterminateState
import led.ColorHSV
import java.awt.Color
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

//A signal animation for the user that the strip is ready for use
class BootCompleteProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mStepsTotal = 720
    var mLEDIndices = intArrayOf(
        0, 10, 20, 30, 40, 50, 60, 70,
        80, 90, 100, 110, 120, 130, 140, 150,
        160, 170, 180, 190, 200, 210, 220, 230,
        240, 250, 260, 270, 280, 290, 299
    )

    override fun start() {
        mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
    }

    public override fun update() {
        if (mStep == 0) {
            start()
        }
        val d = Math.abs(Math.sin(Math.toRadians(mStep.toDouble())))
        val c = Color(0, (d * 255).toInt(), 0)
        for (i in mLEDIndices.indices) {
            mStrip!!.setPixel(mLEDIndices[i], c)
        }
        mStep += 5
        if (mStep == mStepsTotal) {
            mStrip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }
}