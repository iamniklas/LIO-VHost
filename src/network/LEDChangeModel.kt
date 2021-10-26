package network

import com.google.gson.annotations.SerializedName
import led.LEDDataBundle
import procedures.ProcedureType

class LEDChangeModel {
    @SerializedName(value = "procedure")
    var mProcedure: ProcedureType? = null

    @SerializedName(value = "bundle")
    var mBundle: LEDDataBundle? = null
}