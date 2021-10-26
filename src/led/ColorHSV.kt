package led

class ColorHSV(var h: Int, var s: Float, var v: Float) {
    fun ToRGB(): ColorRGB {
        val v255 = (v * 255.0f).toInt()
        if (s == 0.0f) {
            return ColorRGB(v255, v255, v255)
        }
        val hueInterval = Math.floor((h / 60.0f).toDouble()).toInt()
        val f = h / 60.0f - hueInterval.toFloat()
        val p = (v * (1 - s) * 255.0f).toInt()
        val q = (v * (1 - s * f) * 255.0f).toInt()
        val t = (v * (1 - s * (1 - f)) * 255.0f).toInt()
        return if (hueInterval == 0 ||
            hueInterval == 6
        ) ColorRGB(v255, t, p) else if (hueInterval == 1) ColorRGB(q, v255, p) else if (hueInterval == 2) ColorRGB(
            p,
            v255,
            t
        ) else if (hueInterval == 3) ColorRGB(p, q, v255) else if (hueInterval == 4) ColorRGB(
            t,
            p,
            v255
        ) else if (hueInterval == 5) ColorRGB(v255, p, q) else ColorRGB(0, 0, 0)
    }
}