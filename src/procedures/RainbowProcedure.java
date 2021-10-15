package procedures;

import led.ColorHSV;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import procedures.models.Direction;

public class RainbowProcedure extends Procedure {
	
	ColorHSV mColorHSV = new ColorHSV(0, 1.0f, 1.0f);
	
	private float mHueCounter = 0;
	private float[] mHueArrayCounter = new float[300];
	
	private float mRepetitions = 0.75f;
	private float mSpeed = 3;
	private Direction mDirection = Direction.Left;
	
	public RainbowProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mRepetitions = _bundle.repetitions;
		mSpeed = _bundle.speed;
		mDirection = _bundle.direction;
		
		for (int i = 0; i < mHueArrayCounter.length; i++) {
			mHueArrayCounter[i] = Math.abs(i - 150);
		}
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		
		switch (mDirection) {
		case Center:
			for (int i = 0; i < mHueArrayCounter.length; i++) {
				mHueArrayCounter[i] = mHueArrayCounter[i] < 0 ? 360 : mHueArrayCounter[i] - mSpeed;
			}
			break;
		case CenterInvert:
			for (int i = 0; i < mHueArrayCounter.length; i++) {
				mHueArrayCounter[i] = mHueArrayCounter[i] > 360 ? 0 : mHueArrayCounter[i] + mSpeed;
			}
			break;
		case Left:
			mHueCounter = mHueCounter > 360 ? 0 : mHueCounter + mSpeed;
			break;
		case Right:
			mHueCounter = mHueCounter < 0 ? 360 : mHueCounter - mSpeed;
			break;
		
		}
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			if (mDirection == Direction.Center || mDirection == Direction.CenterInvert) {
				mColorHSV.h = (int) ((int) (i * (mRepetitions * (360.0f / (float)LEDStripManager.LED_COUNT))) + mHueArrayCounter[i]) % 360;
				mStrip.setPixel(i, mColorHSV.ToRGB().toSystemColor());
				continue;
			}
			mColorHSV.h = (int) ((int) (i * (mRepetitions * (360.0f / (float)LEDStripManager.LED_COUNT))) + mHueCounter) % 360;
			mStrip.setPixel(i, mColorHSV.ToRGB().toSystemColor());
		}
	}
}