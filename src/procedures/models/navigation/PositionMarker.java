package procedures.models.navigation;

import led.ColorRGB;

public class PositionMarker {
	public String mTag = "";
	public float mXPosition = 0.0f;
	public int mPriority = 0;
	public Size mMarkerSize = Size.Single;
	public Attenutation mAttenuation = Attenutation.Disabled;
	public ColorRGB mColor = ColorRGB.white;
}