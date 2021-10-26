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
import java.nio.charset.StandardCharsets
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

class JsonProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mLEDJsonProcedure: LEDJsonProcedure?
    override fun start() {}
    public override fun update() {
        for (i in 0 until mLEDJsonProcedure!!.mMetaInfo!!.mLedCount) {
            mStrip!!.setPixel(i, mLEDJsonProcedure!!.mLEDStates[mStep]!!.mLEDState[i].toSystemColor())
        }
        mStep += 5
        if (mStep >= mSteps) {
            finishProcedure()
        }
    }

    private fun loadFromFile(_path: String?): LEDJsonProcedure? {
        val encoded: ByteArray
        return try {
            encoded = Files.readAllBytes(Paths.get(_path))
            LEDInterpreter.interpretJson(FileVersions.V0_0_1, String(encoded, StandardCharsets.US_ASCII))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    init {
        mLEDJsonProcedure = loadFromFile(_bundle.path)
        mSteps = mLEDJsonProcedure!!.mLEDStates.size - 1

        //mIsSubProcedure = (boolean) _bundle.get(ProcedureBundleFields.IS_SUB_PROCEDURE);
    }
}