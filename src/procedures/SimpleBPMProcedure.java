package procedures;

import java.awt.Color;
import java.awt.geom.Area;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;

public class SimpleBPMProcedure extends Procedure {

	public ArrayList<Area> mAreas = new ArrayList<Area>();
	
	public int mBpm = 60;
	public float mBeatStep = 0.0f;
	float mCounter = 0.0f;
	
	public SimpleBPMProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		mBeatStep = (60.0f / (float)mBpm)*1000.0f;
		System.out.println("BEATSTEP: " + mBeatStep);
	}

	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		mCounter = mCounter > mBeatStep ? 0.0f : mCounter + 20.0f;
		
		mStrip.setArea(0, LEDStripManager.LED_COUNT, Color.BLACK);
		if (mCounter == 0.0f) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss:SSS");  
			LocalDateTime now = LocalDateTime.now();  
			System.out.println(dtf.format(now));  
			   
			mStrip.setArea(0, LEDStripManager.LED_COUNT, Color.RED);
		}
	}
}