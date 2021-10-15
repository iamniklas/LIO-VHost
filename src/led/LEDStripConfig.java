package led;

import com.google.gson.annotations.SerializedName;

public class LEDStripConfig {
	
	@SerializedName("led_count")
	public Integer mLEDCount = 300;
	
	@SerializedName("gpio_pin")
	public Integer mGpioPin = 18;
	
	@SerializedName("freq")
	public Integer mFrequency = 800000;
	
	@SerializedName("dma")
	public Integer mDma = 10;
	
	@SerializedName("brightness")
	public Integer mBrightness = 255;
	
	@SerializedName("pwm_channel")
	public Integer mPwmChannel = 18;
	
	@SerializedName("invert")
	public Boolean mInvert = false;
	
	@SerializedName("clear_on_exit")
	public Boolean mClearOnExit = true;
}