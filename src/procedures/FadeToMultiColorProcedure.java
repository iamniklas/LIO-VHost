package procedures;

import led.ColorRGB;
import led.ColorRGBA;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

//Action is like FadeToUniformColorProcedure, but individual for every pixel
public class FadeToMultiColorProcedure extends Procedure {

	ColorRGB[] mBaseColor = new ColorRGB[300];
	ColorRGB mTargetColor;
	float mSpeed = 0.0f;
	
	int mCounter = 0;
	int mSteps = 0;
	
	float[] mAlphaStep  = new float[300];
	private float[] mAlphaAddValue = new float[300];
	
	public FadeToMultiColorProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mTargetColor =_bundle.colorPrimary;
		System.out.println(mTargetColor);
		
		mSpeed = _bundle.speed;
		
		mSteps = (int) Math.ceil(mSpeed / (mStrip.getFrametime() / 1000.0f));
		
		for (int i = 0; i < mAlphaAddValue.length; i++) {
			mAlphaAddValue[i] = 1 / (float)mSteps;
		}
		
		start();
		
		//System.out.println("Multi-Fade Steps " + mSteps);
	}
	
	@Override
	public void start() {
		for (int i = 0; i < mBaseColor.length; i++) {
			mBaseColor[i] = ColorRGB.fromSystemColor(mStrip.mStripData.getColorPyPixel(i));
		}
	}
	
	@Override
	void update() {
		for (int i = 0; i < mAlphaStep.length; i++) {
			mAlphaStep[i] += mAlphaAddValue[i];			
			ColorRGBA outputColor = new ColorRGBA(mBaseColor[i].r, mBaseColor[i].g, mBaseColor[i].b, 255 - (int)(mAlphaStep[i] * 255));
			mStrip.setPixel(i, outputColor.toRGB(mTargetColor).toSystemColor());
		}
		mStep++;
		
		if (mStep > mSteps) {
			finishProcedure();
		}
	}
}