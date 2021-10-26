package procedures.models.navigation

import procedures.models.navigation.Attenutation
import led.ColorRGB

class PositionMarker {
    var mTag = ""
    var mXPosition = 0.0f
    var mPriority = 0
    var mMarkerSize = Size.Single
    var mAttenuation = Attenutation.Disabled
    var mColor = ColorRGB.white
}