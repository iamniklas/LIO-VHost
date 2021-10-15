package procedures;

import led.ColorRGB;
import led.LEDDataBundle;

public class LightningProcedure extends Procedure {
	
	private float mSpeed;
	private float mAtenuation;
	
	public LightningProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mSpeed = _bundle.speed;
		mAtenuation = _bundle.value1;
		mSteps = _bundle.duration * 50;
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mStrip.setAllPixels(ColorRGB.white50.dim((float)(Math.abs(Math.sin(mStep / mSpeed)))).toSystemColor());
		
		mSpeed += mAtenuation;
		
		if (mStep > mSteps) {
			finishProcedure(true);
		}
		
		mStep++;
	}
}