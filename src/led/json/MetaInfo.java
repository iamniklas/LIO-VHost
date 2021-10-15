package led.json;

import com.google.gson.annotations.SerializedName;

public class MetaInfo {
	@SerializedName("led_count")
	public int mLedCount;
	
	@SerializedName("repeat_count")
	public int mRepeatCount;
	
	@SerializedName("led_formats")
	public String[] mLEDFormats;
	
	@SerializedName("led_format")
	public String mLEDFormat;
	
	@SerializedName("file_version")
	public String mFileVersion;
}