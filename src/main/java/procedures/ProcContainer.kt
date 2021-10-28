package procedures

import led.LEDStripManager

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