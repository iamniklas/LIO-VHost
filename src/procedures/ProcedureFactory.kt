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
import procedures.JavascriptProcedure
import procedures.models.IndeterminateState
import led.ColorHSV
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

object ProcedureFactory {
    fun getProcedure(_types: ProcedureType?, _bundle: LEDDataBundle): Procedure? {
        return when (_types) {
            ProcedureType.BootComplete -> BootCompleteProcedure(_bundle)
            ProcedureType.ColorInstantSet -> ColorInstantSetProcedure(_bundle)
            ProcedureType.FadeInFadeOut -> FadeInFadeOutProcedure(_bundle)
            ProcedureType.FadeToMultiColor -> FadeToMultiColorProcedure(_bundle)
            ProcedureType.FadeToUniformColor -> FadeToUniformColorProcedure(_bundle)
            ProcedureType.Fill -> FillStripProcedure(_bundle)
            ProcedureType.FillInterpolated -> FillStripInterpolatedProcedure(_bundle)
            ProcedureType.Rainbow -> RainbowProcedure(_bundle)
            ProcedureType.RainbowMono -> RainbowMonoProcedure(_bundle)
            ProcedureType.SimpleBPM -> SimpleBPMProcedure(_bundle)
            ProcedureType.MultiProcedure -> MultiProcedure(_bundle)
            ProcedureType.Blink -> BlinkProcedure(_bundle)
            ProcedureType.Glitter -> GlitterProcedure(_bundle)
            ProcedureType.Navigation -> NavigationProcedure(_bundle)
            ProcedureType.Progress -> ProgressProcedure(_bundle)
            ProcedureType.Lightning -> LightningProcedure(_bundle)
            ProcedureType.NoLongerReady -> NoLongerReadyProcedure(_bundle)
            ProcedureType.JsonProcedure -> JsonProcedure(_bundle)
            ProcedureType.Javascript -> JavascriptProcedure(_bundle)
            else -> null
        }
    }
}