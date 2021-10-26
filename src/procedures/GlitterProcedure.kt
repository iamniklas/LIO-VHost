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
import procedures.models.IndeterminateState
import led.ColorHSV
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.util.*

class GlitterProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBaseColor: ColorRGB?
    var mLedX = ArrayList<Float>()
    var someDumbCounter = 0.0f
    var mSpeed = 0.1f
    var tcount = 0.0f
    override fun start() {
        val random = Random()
        for (i in 0 until LEDStripManager.LED_COUNT) {
            mLedX[i] = (random.nextFloat() * (Math.PI * 2 - 0.0f) + 0.0f).toFloat()
        }
    }

    public override fun update() {
        tcount += mSpeed
        if (tcount > Math.PI * 2) tcount = 0f
        for (i in 0 until LEDStripManager.LED_COUNT) {
            mStrip!!.setPixel(
                i, mBaseColor!!.dim(
                    Math.abs(Math.sin((tcount + mLedX[i]).toDouble())).toFloat()
                ).toSystemColor()
            )
        }
    }

    init {
        mBaseColor = _bundle.colorPrimary
        mSpeed = _bundle.speed!!
        for (i in 0 until LEDStripManager.LED_COUNT) {
            mLedX.add(0.0f)
        }
        start()
    }
}