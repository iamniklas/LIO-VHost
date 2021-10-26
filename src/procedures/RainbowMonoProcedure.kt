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
import procedures.models.IndeterminateState
import led.ColorHSV
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

class RainbowMonoProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mColorHSV = ColorHSV(0, 1.0f, 1.0f)
    var mHueCounter = 0
    var mRepetitions = 1f
    var mSpeed = 1f
    override fun start() {}
    public override fun update() {
        mColorHSV.h = (if (mColorHSV.h > 360.0f) 0 else mColorHSV.h + mSpeed).toInt()
        mStrip!!.setAllPixels(mColorHSV.ToRGB().toSystemColor())
    }

    init {
        mSpeed = _bundle.speed!!
    }
}