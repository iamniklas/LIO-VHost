package procedures;

import java.awt.Color;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class BlinkProcedure extends Procedure {
	
	Color mBlinkColor;
	int mFrames = 10;
	int mModulo = 2;
	
	public BlinkProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mBlinkColor = _bundle.colorPrimary.toSystemColor();
		mFrames = _bundle.duration;
		mModulo = _bundle.modulo;
		
		mSteps = mFrames;
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mStep++;
		
		if (mStep % mModulo == 0) {
			mStrip.setAllPixels(mBlinkColor);
		}
		else {
			mStrip.setAllPixels(ColorRGB.black.toSystemColor());
		}
		
		if (mStep == mSteps) {
			mStrip.setAllPixels(ColorRGB.black.toSystemColor());
			finishProcedure();
		}
	}
}