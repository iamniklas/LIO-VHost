package led.json

import com.google.gson.annotations.SerializedName

class MetaInfo {
    @SerializedName("led_count")
    var mLedCount = 0

    @SerializedName("repeat_count")
    var mRepeatCount = 0

    @SerializedName("led_formats")
    lateinit var mLEDFormats: Array<String>

    @SerializedName("led_format")
    var mLEDFormat: String? = null

    @SerializedName("file_version")
    var mFileVersion: String? = null
}