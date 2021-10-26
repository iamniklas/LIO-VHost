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
import procedures.models.IndeterminateState
import led.ColorHSV
import java.awt.Color
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

//A signal animation to notify the user that the strip is no longer ready for any reason (internal error, network disconnect, etc.)
class NoLongerReadyProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mTotalSteps = 60
    var mRedLightActive = false
    override fun start() {}
    public override fun update() {
        if (mStep % 5 == 0) {
            if (mRedLightActive) {
                mStrip!!.setAllPixels(Color.BLACK)
            } else {
                mStrip!!.setAllPixels(Color.RED)
            }
            mRedLightActive = !mRedLightActive
        }
        mStep++
        if (mStep > mTotalSteps) {
            mStrip!!.setAllPixels(Color.BLACK)
            finishProcedure()
        }
    }
}