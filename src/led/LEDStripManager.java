package led;

import java.awt.Color;

import javax.swing.JLabel;

import led.json.LEDStatus;
import network.LEDChangeModel;
import procedures.*;
import procedures.models.Direction;

public class LEDStripManager implements ProcedureCalls {
	private final LEDStripConfig mLedStripConfig = new LEDStripConfig();
	
	public static final int LED_COUNT = 300;
	
	public LEDStrip mStripData = new LEDStrip(LED_COUNT);
	
	public ProcContainer mProcContainer = new ProcContainer(this);
	
	private int mFrametime = 16;
	
	public static LEDStatus mLEDStatus = new LEDStatus();
	
	public LEDStripRenderer mStrip = new LEDStripRenderer();
	
	public LEDStripManager(JLabel[] _leds, boolean _clearOnExit) throws InterruptedException {
		System.out.println("LED Strip \tINIT \tSTART");
		
		mFrametime = 16;
		LEDStripRenderer.leds = _leds;
		
		System.out.println("LED Strip \tINIT \tDONE");
		
		LEDChangeModel changeModel = new LEDChangeModel();
		
		LEDDataBundle bundle = new LEDDataBundle();
		bundle.ledStrip = this;
		bundle.procedureCalls = this;
		bundle.data = "indices = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 299]; function update(strip, procedure, step, totalSteps) {d = Math.abs(Math.sin(step * Math.PI/180)); for(i = 0; i < indices.length; i++) { strip.setPixel(indices[i], 0, parseInt(d * 255), 0); } if(step >= totalSteps) { procedure.finish(); } }";
		
		changeModel.mProcedure = ProcedureType.Javascript;
		changeModel.mBundle = bundle;
		
		JavascriptProcedure proc = (JavascriptProcedure) ProcedureFactory.getProcedure(changeModel.mProcedure, changeModel.mBundle);
		mProcContainer.queueProcedure(proc);
	}
	
	public void update() {
		mProcContainer.update();
		mProcContainer.postUpdate();
		
		for (int i = 0; i < mStripData.mStrip.size(); i++) {
			mStrip.setPixel(i, mStripData.mStrip.get(i));
		}
		mStrip.render();
		try {
			Thread.sleep(mFrametime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getFrametime() {
		return mFrametime;
	}
	
	/**
	 * @param _pxl The pixel index
	 * @param _color The color to set the pixel
	 */
	public void setPixel(int _pxl, Color _color) {
		_color = new Color(Math.max(0, _color.getRed()), Math.max(0, _color.getGreen()), Math.max(0, _color.getBlue()));
		mStripData.mStrip.set(_pxl, _color);
	}
	
	public void setPixel(int _pxl, int _r, int _g, int _b) {
		Color c = new Color(Math.max(0, _r), Math.max(0, _g), Math.max(0, _b));
		mStripData.mStrip.set(_pxl, c);
	}
	
	/**
	 * @param _startPxl The start index for the area
	 * @param _endPxl The end index for the area
	 * @param _color The color to fill the strip area with
	 */
	public void setArea(int _startPxl, int _endPxl, Color _color) {
		for (int i = _startPxl; i < _endPxl; i++) {
			mStripData.mStrip.set(i, _color);
		}
	}
	
	/**
	 * @param _color The color to fill the strip with
	 */
	public void setAllPixels(Color _color) {
		for (int i = 0; i < LED_COUNT; i++) {
			mStripData.mStrip.set(i, _color);
		}
	}

	@Override
	public void onProcedureStart(Procedure _proc) {
		_proc.start();
	}

	@Override
	public void onProcedureQueued() {

	}
	
	@Override
	public void onProcedureFinish() {

	}

}