package network;

import com.google.gson.annotations.SerializedName;

import led.LEDDataBundle;
import procedures.ProcedureType;

public class LEDChangeModel {
	
	@SerializedName(value="procedure")
	public ProcedureType mProcedure;
	
	@SerializedName(value="bundle")
	public LEDDataBundle mBundle;
}