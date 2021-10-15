package procedures;

import java.util.ArrayList;

import led.LEDDataBundle;
import led.LEDStripManager;
import led.ProcedureBundleFields;
import procedures.models.navigation.PositionMarker;

public class NavigationProcedure extends Procedure {
	
	public ArrayList<PositionMarker> mMarkers = new ArrayList<PositionMarker>();
	
	public NavigationProcedure(LEDDataBundle _bundle) {
		super(_bundle);
	}

	@Override
	public void start() {
		
	}

	@Override
	void update() {
		for (int i = 0; i < mMarkers.size(); i++) {
			PositionMarker marker = mMarkers.get(i);
			switch (marker.mMarkerSize) {
			case Large:
				
				break;
			case Medium:
				
				break;
			case Small:
				
				break;
			case Single:
				mStrip.setPixel((int)(marker.mXPosition * 180.0f), marker.mColor.toSystemColor());
				break;
			
			default:
				break;
			
			}
		}
		
		finishProcedure();
	}
}