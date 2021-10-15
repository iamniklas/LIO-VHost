package procedures;

import java.util.ArrayList;
import java.util.Random;

import led.ColorRGB;
import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class GlitterProcedure extends Procedure {

	ColorRGB mBaseColor;
	
	ArrayList<Float> mLedX = new ArrayList<Float>();
	float someDumbCounter = 0.0f;
	
	float mSpeed = 0.1f;
	float tcount = 0.0f;
	
	public GlitterProcedure(LEDDataBundle _bundle) {
		super(_bundle);

		mBaseColor = _bundle.colorPrimary;
		mSpeed = _bundle.speed;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mLedX.add(0.0f);
		}
		
		start();
	}
	
	@Override
	public void start() {
		Random random = new Random();
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mLedX.set(i, (float) (random.nextFloat() * (Math.PI * 2 - 0.0f) + 0.0f));
		}
	}

	@Override
	void update() {
		tcount += mSpeed;
		if (tcount > Math.PI * 2) tcount = 0;
		
		for (int i = 0; i < LEDStripManager.LED_COUNT; i++) {
			mStrip.setPixel(i, mBaseColor.dim((float)Math.abs(Math.sin(tcount + mLedX.get(i)))).toSystemColor());
		}
	}
}