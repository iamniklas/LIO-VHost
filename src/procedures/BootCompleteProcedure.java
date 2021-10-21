package procedures;

import java.awt.Color;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

//A signal animation for the user that the strip is ready for use
public class BootCompleteProcedure extends Procedure {

	int mStepsTotal = 720;
	
	int[] mLEDIndices = new int[] {0, 10, 20, 30, 40, 50, 60, 70,
								   80, 90, 100, 110, 120, 130, 140, 150,
								   160, 170, 180, 190, 200, 210, 220, 230,
								   240, 250, 260, 270, 280, 290, 299};
	
	public BootCompleteProcedure(LEDDataBundle _bundle) {
		super(_bundle);
	}

	@Override
	public void start() {
		mStrip.setAllPixels(ColorRGB.black.toSystemColor());
	}
	
	@Override
	public void update() {
		if(mStep == 0) {
			start();
		}
		
		double d = Math.abs(Math.sin(Math.toRadians(mStep)));
		
		Color c = new Color(0, (int) (d * 255), 0);
		
		for (int i = 0; i < mLEDIndices.length; i++) {
			mStrip.setPixel(mLEDIndices[i], c);
		}
		
		mStep+=5;
		
		if(mStep == mStepsTotal) {
			mStrip.setAllPixels(Color.BLACK);
			finishProcedure();
		}
	}
}