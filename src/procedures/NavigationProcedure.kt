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
import procedures.models.navigation.Size
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import java.util.ArrayList

class NavigationProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mMarkers = ArrayList<PositionMarker>()
    override fun start() {}
    public override fun update() {
        for (i in mMarkers.indices) {
            val marker = mMarkers[i]
            when (marker.mMarkerSize) {
                Size.Large -> {
                }
                Size.Medium -> {
                }
                Size.Small -> {
                }
                Size.Single -> mStrip!!.setPixel((marker.mXPosition * 180.0f).toInt(), marker.mColor.toSystemColor())
                else -> {
                }
            }
        }
        finishProcedure()
    }
}