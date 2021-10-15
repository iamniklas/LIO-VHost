package procedures;

import java.awt.Color;

import led.ColorRGB;
import led.LEDDataBundle;
import led.ProcedureBundleFields;

//Fade one given color in, then fade out to black
public class FadeInFadeOutProcedure extends Procedure {
	
	private float[] mColorPartModifier = {1.0f, 1.0f, 1.0f};
	private int mTotalSteps = 180;
	
	public FadeInFadeOutProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		Color targetColor = _bundle.colorPrimary.toSystemColor();
		mColorPartModifier[0] = (float) targetColor.getRed() / 255.0f;
		mColorPartModifier[1] = (float) targetColor.getGreen() / 255.0f;
		mColorPartModifier[2] = (float) targetColor.getBlue() / 255.0f;	
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		float d = (float) Math.abs(Math.sin(Math.toRadians(mStep)));
		
		mStrip.setAllPixels(new Color(
				(int) (mColorPartModifier[0] * d * 255.0f), 
				(int) (mColorPartModifier[1] * d * 255.0f), 
				(int) (mColorPartModifier[2] * d * 255.0f)));
		
		mStep+=5;
		
		if (mStep >= mTotalSteps) {
			mStrip.setAllPixels(Color.BLACK);
			finishProcedure();
		}
	}
}