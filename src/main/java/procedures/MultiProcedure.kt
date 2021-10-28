package procedures

import led.LEDDataBundle

class MultiProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    lateinit var mProcedures: Array<Procedure>
    override fun start() {
        for (procedure in mProcedures) {
            procedure.start()
            //mSteps = procedure.mSteps > mSteps ? procedure.mSteps : mSteps; 
        }
        mSteps = 60
    }

    public override fun update() {
        mStep++
        for (procedure in mProcedures) {
            procedure.update()
        }
        if (mStep >= mSteps) {
            finishProcedure()
        }
    }
}