package procedures

import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.procedures.Procedure
import procedures.models.navigation.PositionMarker
import procedures.models.navigation.Size

class NavigationProcedure(_bundle: LEDDataBundle) : Procedure(_bundle) {
    var mMarkers = ArrayList<PositionMarker>()
    override fun start() {}
    public override fun update() {
        for (i in mMarkers.indices) {
            val marker = mMarkers[i]
            when (marker.mMarkerSize) {
                Size.Large -> {
                }
                Size.Medium -> {
                }
                Size.Small -> {
                }
                Size.Single -> strip!!.setPixel((marker.mXPosition * 180.0f).toInt(), marker.mColor.toSystemColor())
                else -> {
                }
            }
        }
        finishProcedure()
    }
}