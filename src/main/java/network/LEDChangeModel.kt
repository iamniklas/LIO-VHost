package network

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.google.gson.annotations.SerializedName
import procedures.ProcedureType

class LEDChangeModel {
    @SerializedName(value = "procedure")
    var mProcedure: ProcedureType? = null

    @SerializedName(value = "bundle")
    var mBundle: LEDDataBundle? = null
}