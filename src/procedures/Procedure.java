package procedures;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;

public abstract class Procedure {
	public LEDStripManager mStrip;
	public ProcedureCalls mCallbacks;
	
	int mStep = 0;
	int mSteps = 0;
	
	int mModulo = 1;
	boolean mModuloInvert = false;
	
	protected boolean mIsSubProcedure = false;
	
	public Procedure(LEDDataBundle _bundle) {
		mStrip = _bundle.ledStrip;
		mCallbacks = _bundle.procedureCalls;
		
		if (_bundle.puModulo != null) {
			mModulo = _bundle.puModulo;
		}
		
		if (_bundle.puModuloInvert != null) {
			mModuloInvert = _bundle.puModuloInvert;
		}
	}
	
	public abstract void start();
	abstract void update();
	void postUpdate() {
		if(mModulo < 2) return;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			if (mModuloInvert) {
				if (i % mModulo != 0) {
					mStrip.setPixel(i, ColorRGB.black.toSystemColor());					
				}
			}
			else {
				if (i % mModulo == 0) {
					mStrip.setPixel(i, ColorRGB.black.toSystemColor());					
				}
			}
		}
		
		if (mStep > mSteps) {
			finishProcedure();
		}
	}
	
	protected void finishProcedure(boolean _clearStrip) {
		mStrip.mProcContainer.removeCurrentProcedure();
		if (_clearStrip) {
			mStrip.setAllPixels(ColorRGB.black.toSystemColor());
		}
		mCallbacks.onProcedureFinish();
	}
	
	protected void finishProcedure() {
		mStrip.mProcContainer.removeCurrentProcedure();
		mCallbacks.onProcedureFinish();
	}
}
