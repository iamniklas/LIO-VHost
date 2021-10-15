package procedures;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class MultiProcedure extends Procedure {

	Procedure[] mProcedures;
	
	public MultiProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		//TODO Re-enable
		//mProcedures = (Procedure[]) _bundle.get(ProcedureBundleFields.SUB_BUNDLE);
	}
	
	@Override
	public void start() {
		for (Procedure procedure : mProcedures) {
			procedure.start();
			//mSteps = procedure.mSteps > mSteps ? procedure.mSteps : mSteps; 
		}
		mSteps = 60;
	}

	@Override
	void update() {
		mStep++;
		for (Procedure procedure : mProcedures) {
			procedure.update();
		}
		
		if (mStep >= mSteps) {
			finishProcedure();
		}
	}
}