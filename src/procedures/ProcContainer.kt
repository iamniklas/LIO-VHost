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
import java.util.ArrayList

class ProcContainer(var mLedStrip: LEDStripManager) {
    var mProcedures = ArrayList<Procedure>()
    fun queueProcedure(_proc: Procedure) {
        mProcedures.add(_proc)
        _proc.mCallbacks!!.onProcedureQueued()
    }

    val activeProcedure: Procedure?
        get() = if (mProcedures.size > 0) mProcedures[0] else null

    fun removeCurrentProcedure() {
        if (mProcedures.size > 0) {
            mProcedures.removeAt(0)
        }
        if (mProcedures.size > 0) {
            mProcedures[0].mCallbacks!!.onProcedureStart(mProcedures[0])
        }
    }

    fun update() {
        if (mProcedures.size < 1) return
        mProcedures[0].update()
    }

    fun postUpdate() {
        if (mProcedures.size < 1) return
        mProcedures[0].postUpdate()
    }
}