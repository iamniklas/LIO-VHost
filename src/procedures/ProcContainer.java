package procedures;

import java.util.ArrayList;

import led.LEDStripManager;

public class ProcContainer {
	LEDStripManager mLedStrip;
	ArrayList<Procedure> mProcedures = new ArrayList<Procedure>();
	
	public ProcContainer(LEDStripManager _ledStripMng) {
		mLedStrip = _ledStripMng;
	}
	
	public void queueProcedure(Procedure _proc) {
		mProcedures.add(_proc);
		_proc.mCallbacks.onProcedureQueued();
	}
	
	public Procedure getActiveProcedure() {
		return mProcedures.size() > 0 ? mProcedures.get(0) : null;
	}
	
	public void removeCurrentProcedure() {
		if (mProcedures.size() > 0) {
			mProcedures.remove(0);
		}
		
		if (mProcedures.size() > 0) {
			mProcedures.get(0).mCallbacks.onProcedureStart(mProcedures.get(0));
		}
	}
	
	public void update() {
		if(mProcedures.size() < 1) return;
		
		mProcedures.get(0).update();
	}
	
	public void postUpdate() {
		if(mProcedures.size() < 1) return;
		
		mProcedures.get(0).postUpdate();
	}
}