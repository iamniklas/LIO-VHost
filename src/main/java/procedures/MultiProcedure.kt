package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.procedures.Procedure

class MultiProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    lateinit var mProcedures: Array<Procedure>
    override fun start() {
        for (procedure in mProcedures) {
            procedure.start()
            //mSteps = procedure.mSteps > mSteps ? procedure.mSteps : mSteps; 
        }
        steps = 60
    }

    public override fun update() {
        step++
        for (procedure in mProcedures) {
            procedure.update()
        }
        if (step >= steps) {
            finishProcedure()
        }
    }
}