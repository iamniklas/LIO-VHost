package procedures;

import java.awt.Color;

import led.LEDDataBundle;

//Set every strip's pixel to a uniform color in a instant 1 frame animation
public class ColorInstantSetProcedure extends Procedure {

	Color mTargetColor;
	
	public ColorInstantSetProcedure(LEDDataBundle _bundle) {		
		super(_bundle);
		mTargetColor = _bundle.colorPrimary.toSystemColor();
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mStrip.setAllPixels(mTargetColor);
		postUpdate();
		finishProcedure();
	}
}