package procedures

import led.LEDDataBundle
import javax.script.ScriptEngine
import javax.script.Invocable
import java.lang.NoSuchMethodException
import javax.script.ScriptException
import javax.script.ScriptEngineManager

class JavascriptProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var jsStart = ""
    var jsUpdate: String?
    var runtime: ScriptEngine? = null
    var invokeEngine: Invocable? = null
    var eval: Any? = null
    override fun start() {}
    override fun update() {
        try {
            invokeEngine!!.invokeFunction("update", mStrip, this, mStep, 720)
            mStep += 5
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: ScriptException) {
            e.printStackTrace()
        }
    }

    fun finish() {
        finishProcedure()
    }

    init {
        runtime = null
        invokeEngine = null
        eval = null
        runtime = ScriptEngineManager().getEngineByName("javascript")
        jsUpdate = _bundle.data
        try {
            eval = runtime?.eval(jsUpdate.toString())
            invokeEngine = runtime as Invocable?
        } catch (e: ScriptException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }
}