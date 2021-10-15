package led.json;

import com.google.gson.annotations.SerializedName;

import led.ColorRGB;

public class LEDStateArray {
	@SerializedName("led_state")
	public ColorRGB[] mLEDState;
}