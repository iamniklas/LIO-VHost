package led.json;

import com.google.gson.annotations.SerializedName;

public class LEDJsonProcedure {
	@SerializedName("meta")
	public MetaInfo mMetaInfo;
	
	@SerializedName("led_state")
	public LEDStateArray[] mLEDStates;
}