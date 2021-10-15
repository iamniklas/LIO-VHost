package procedures;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import procedures.models.IndeterminateState;

public class ProgressProcedure extends Procedure {

	private float mStart = 0.0f;
	private float mProgress = 0.0f;
	private ColorRGB mColor;
	private boolean mPulsing = false;
	private boolean mIndeterminate = false;
	private IndeterminateState mIndeterminateState = IndeterminateState.Fill;
	private float mSineX = 0.0f;
	private float mSpeed = 0.2f;
	
	public ProgressProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mColor = _bundle.colorPrimary;
		mStart = _bundle.value1;
		mProgress = _bundle.value2;
		mIndeterminate = _bundle.indeterminate;
		mPulsing = _bundle.pulsating;
		mSpeed = _bundle.speed;
		mSteps = 1;
		
		start();
	}

	@Override
	public void start() {		
		mStrip.setAllPixels(ColorRGB.black.toSystemColor());
	}

	@Override
	void update() {
		mStrip.setAllPixels(ColorRGB.black.toSystemColor());
		
		if (mPulsing) {
			mSineX += mSpeed;
			if (mSineX > Math.PI * 2) mSineX = 0;
			
			mStrip.setArea((int)(LEDStripManager.LED_COUNT * mStart), 
						   (int)(LEDStripManager.LED_COUNT * mProgress), 
						   mColor.dim((float)Math.abs(Math.sin(mSineX))).toSystemColor());
		}
		else if(mIndeterminate) {
			switch (mIndeterminateState) {
			case Clear:
				mStart += mSpeed;
				
				if (mStart >= 1.0f) {
					mIndeterminateState = IndeterminateState.ClearDone;
				}
				break;
				
			case ClearDone:
				mStart = 0.0f;
				mProgress = 0.0f;
				mIndeterminateState = IndeterminateState.Fill;
				break;
				
			case Fill:
				mProgress += mSpeed;
				
				if (mProgress >= 1.0f) {
					mIndeterminateState = IndeterminateState.FillDone;
				}
				break;
				
			case FillDone:
				mStart = 0.0f;
				mProgress = 1.0f;
				mIndeterminateState = IndeterminateState.Clear;
				break;
			}
			
			mStrip.setArea(Math.min(Math.max((int)(LEDStripManager.LED_COUNT * mStart), 	0), 300), 
						   Math.min(Math.max((int)(LEDStripManager.LED_COUNT * mProgress), 	0), 300), 
						   mColor.toSystemColor());
		}
		else {
			mStrip.setArea((int)(LEDStripManager.LED_COUNT * mStart), 
						   (int)(LEDStripManager.LED_COUNT * mProgress), 
						   mColor.toSystemColor());
		}
	}
}