package procedures.models.navigation

import com.github.iamniklas.liocore.led.colorspace.ColorRGB

class PositionMarker {
    var mTag = ""
    var mXPosition = 0.0f
    var mPriority = 0
    var mMarkerSize = Size.Single
    var mAttenuation = Attenutation.Disabled
    var mColor = ColorRGB.white
}