package procedures;

import java.awt.Color;

import led.LEDDataBundle;
import led.LEDStripManager;
import procedures.models.Direction;

//Animation to fill the strip from a given direction (left, right, center, bounds) with a given color 
public class FillStripProcedure extends Procedure {
	private Color mFillColor = Color.BLACK;
	
	public int mSpeed = 1;
	public Direction mDirection = Direction.Left;
	
	public FillStripProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mFillColor = _bundle.colorPrimary.toSystemColor();
		mSpeed = Math.round(_bundle.speed);
		mDirection = _bundle.direction;
		
		mSteps = LEDStripManager.LED_COUNT;
		
		if (mDirection == Direction.Center || mDirection == Direction.CenterInvert) {
			mSteps /= 2;
		}
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		switch (mDirection) {
		case Center:
			for (int i = 0; i < mSpeed; i++) {
				mStrip.setPixel((LEDStripManager.LED_COUNT / 2 - 1) + mStep + i, mFillColor);
				mStrip.setPixel((LEDStripManager.LED_COUNT / 2 - 1) - mStep - i, mFillColor);	
			}
			break;
		case CenterInvert:
			for (int i = 0; i < mSpeed; i++) {
				mStrip.setPixel(mStep + i, mFillColor);
				mStrip.setPixel((LEDStripManager.LED_COUNT - 1) - mStep - i, mFillColor);	
			}
			break;
		case Left:
			for (int i = 0; i < mSpeed; i++) {
				mStrip.setPixel(mStep + i, mFillColor);	
			}
			break;
		case Right:
			for (int i = 0; i < mSpeed; i++) {
				mStrip.setPixel((LEDStripManager.LED_COUNT - 1) - mStep - i, mFillColor);	
			}
			break;
		default:
			break;
			
		}
		
		mStep += mSpeed;
		
		if (mStep >= mSteps) {
			if (!mIsSubProcedure) {
				finishProcedure();				
			}
		}
	}
}

/*
 * Ideas:
 * - Ease interpolation function
 * - Tiling (single fill as normal, double fill with e.g. "two center points" to fill from
 */