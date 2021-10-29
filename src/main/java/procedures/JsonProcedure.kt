package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.procedures.Procedure
import led.json.LEDJsonProcedure
import led.json.interpreter.FileVersions
import led.json.interpreter.LEDInterpreter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths

class JsonProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mLEDJsonProcedure: LEDJsonProcedure?
    override fun start() {}
    public override fun update() {
        for (i in 0 until mLEDJsonProcedure!!.mMetaInfo!!.mLedCount) {
            strip!!.setPixel(i, mLEDJsonProcedure!!.mLEDStates[step]!!.mLEDState[i].toSystemColor())
        }
        step += 5
        if (step >= steps) {
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
        steps = mLEDJsonProcedure!!.mLEDStates.size - 1

        //mIsSubProcedure = (boolean) _bundle.get(ProcedureBundleFields.IS_SUB_PROCEDURE);
    }
}