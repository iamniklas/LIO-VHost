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
import procedures.models.Direction
import java.awt.Color
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
class FillStripProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    private var mFillColor = Color.BLACK
    var mSpeed = 1
    var mDirection: Direction? = Direction.Left
    override fun start() {}
    public override fun update() {
        when (mDirection) {
            Direction.Center -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT / 2 - 1 + mStep + i, mFillColor)
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT / 2 - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            Direction.CenterInvert -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(mStep + i, mFillColor)
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            Direction.Left -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(mStep + i, mFillColor)
                    i++
                }
            }
            Direction.Right -> {
                var i = 0
                while (i < mSpeed) {
                    mStrip!!.setPixel(LEDStripManager.LED_COUNT - 1 - mStep - i, mFillColor)
                    i++
                }
            }
            else -> {
            }
        }
        mStep += mSpeed
        if (mStep >= mSteps) {
            if (!mIsSubProcedure) {
                finishProcedure()
            }
        }
    }

    init {
        mFillColor = _bundle.colorPrimary!!.toSystemColor()
        mSpeed = Math.round(_bundle.speed!!)
        mDirection = _bundle.direction
        mSteps = LEDStripManager.LED_COUNT
        if (mDirection == Direction.Center || mDirection == Direction.CenterInvert) {
            mSteps /= 2
        }
    }
}