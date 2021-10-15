package procedures;

import led.ColorRGB;
import led.ColorRGBA;
import led.LEDDataBundle;

//Fade every pixel from a base color to a target color
public class FadeToUniformColorProcedure extends Procedure {
	
	ColorRGB mBaseColor;
	ColorRGB mTargetColor;
	float mDuration = 0.0f;
	
	int mCounter = 0;
	int mSteps = 0;
	
	float mAlphaStep = 0.0f;
	private float mAlphaAddValue = 0.0f;
	
	public FadeToUniformColorProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mTargetColor = _bundle.colorPrimary;
		mBaseColor = _bundle.colorSecondary;
		mDuration = _bundle.duration;
		
		mSteps = (int) Math.ceil(mDuration / (mStrip.getFrametime() / 1000.0f));
		
		mAlphaAddValue = 1 / (float)mSteps;
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mCounter++;
		mAlphaStep += mAlphaAddValue;
		
		ColorRGBA outputColor = new ColorRGBA(mBaseColor.r, mBaseColor.g, mBaseColor.b, (int)(mAlphaStep * 255));
		
		mStrip.setAllPixels(outputColor.toRGB(mTargetColor).toSystemColor());
		
		if (mCounter > mSteps) {
			mStrip.setAllPixels(mBaseColor.toSystemColor());
			finishProcedure();
		}
	}
}