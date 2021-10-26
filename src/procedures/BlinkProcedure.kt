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
import java.awt.Color
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

class BlinkProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mBlinkColor: Color
    var mFrames = 10
    override var mModulo = 2
    override fun start() {}
    public override fun update() {
        mStep++
        if (mStep % mModulo == 0) {
            mStrip!!.setAllPixels(mBlinkColor)
        } else {
            mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
        }
        if (mStep == mSteps) {
            mStrip!!.setAllPixels(ColorRGB.black.toSystemColor())
            finishProcedure()
        }
    }

    init {
        mBlinkColor = _bundle.colorPrimary!!.toSystemColor()
        mFrames = _bundle.duration!!
        mModulo = _bundle.modulo!!
        mSteps = mFrames
    }
}