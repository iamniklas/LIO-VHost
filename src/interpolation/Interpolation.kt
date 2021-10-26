package interpolation

import interpolation.Interpolation
import interpolation.InterpolationType
import kotlin.math.pow

object Interpolation {
    private const val c1 = 1.70158f
    private const val c2 = c1 * 1.525f
    private const val c3 = c1 + 1.0f
    private const val c4 = (2 * Math.PI).toFloat() / 3.0f
    private const val c5 = (2 * Math.PI).toFloat() / 4.5f
    private const val n1 = 7.5625f
    private const val d1 = 2.75f
    @JvmStatic
	fun getInterpolationValue(_value: Float, _interpolationType: InterpolationType?): Float {
        return when (_interpolationType) {
            InterpolationType.EaseInSine -> easeInSine(_value)
            InterpolationType.EaseOutSine -> easeOutSine(_value)
            InterpolationType.EaseInOutSine -> easeInOutSine(_value)
            InterpolationType.EaseInQuad -> easeInQuad(_value)
            InterpolationType.EaseOutQuad -> easeOutQuad(_value)
            InterpolationType.EaseInOutQuad -> easeInOutQuad(_value)
            InterpolationType.EaseInCubic -> easeInCubic(_value)
            InterpolationType.EaseOutCubic -> easeOutCubic(_value)
            InterpolationType.EaseInOutCubic -> easeInOutCubic(_value)
            InterpolationType.EaseInQuart -> easeInQuart(_value)
            InterpolationType.EaseOutQuart -> easeOutQuart(_value)
            InterpolationType.EaseInOutQuart -> easeInOutQuart(_value)
            InterpolationType.EaseInQuint -> easeInQuint(_value)
            InterpolationType.EaseOutQuint -> easeOutQuint(_value)
            InterpolationType.EaseInOutQuint -> easeInOutQuint(_value)
            InterpolationType.EaseInExpo -> easeInExpo(_value)
            InterpolationType.EaseOutExpo -> easeOutExpo(_value)
            InterpolationType.EaseInOutExpo -> easeInOutExpo(_value)
            InterpolationType.EaseInCirc -> easeInCirc(_value)
            InterpolationType.EaseOutCirc -> easeOutCirc(_value)
            InterpolationType.EaseInOutCirc -> easeInOutCirc(_value)
            InterpolationType.EaseInBack -> easeInBack(_value)
            InterpolationType.EaseOutBack -> easeOutBack(_value)
            InterpolationType.EaseInOutBack -> easeInOutBack(_value)
            InterpolationType.EaseInElastic -> easeInElastic(_value)
            InterpolationType.EaseOutElastic -> easeOutElastic(_value)
            InterpolationType.EaseInOutElastic -> easeInOutElastic(_value)
            InterpolationType.EaseInBounce -> easeInBounce(_value)
            InterpolationType.EaseOutBounce -> easeOutBounce(_value)
            InterpolationType.EaseInOutBounce -> easeInOutBounce(_value)
            InterpolationType.Linear -> _value
            else -> _value
        }
    }

    private fun easeInSine(_value: Float): Float {
        return 1.0f - Math.cos(_value * Math.PI / 2.0f).toFloat()
    }

    private fun easeOutSine(_value: Float): Float {
        return Math.sin(_value * Math.PI / 2.0f).toFloat()
    }

    private fun easeInOutSine(_value: Float): Float {
        return (-(Math.cos(Math.PI * _value) - 1.0f)).toFloat() / 2.0f
    }

    private fun easeInQuad(_value: Float): Float {
        return Math.pow(_value.toDouble(), 2.0).toFloat()
    }

    private fun easeOutQuad(_value: Float): Float {
        return 1 - (1 - _value) * (1 - _value)
    }

    private fun easeInOutQuad(_value: Float): Float {
        return if (_value < 0.5f) 2 * _value * _value else 1 - Math.pow(-2.0 * _value + 2.0, 2.0).toFloat() / 2
    }

    private fun easeInCubic(_value: Float): Float {
        return Math.pow(_value.toDouble(), 3.0).toFloat()
    }

    private fun easeOutCubic(_value: Float): Float {
        return 1.0f - Math.pow(1.0 - _value, 3.0).toFloat()
    }

    private fun easeInOutCubic(_value: Float): Float {
        return if (_value < 0.5f) 4.0f * _value * _value * _value else 1.0f - Math.pow(-2.0 * _value + 2.0, 3.0)
            .toFloat() / 2.0f
    }

    private fun easeInQuart(_value: Float): Float {
        return Math.pow(_value.toDouble(), 4.0).toFloat()
    }

    private fun easeOutQuart(_value: Float): Float {
        return 1.0f - Math.pow((1 - _value).toDouble(), 4.0).toFloat()
    }

    private fun easeInOutQuart(_value: Float): Float {
        return if (_value < 0.5f) 8 * Math.pow(_value.toDouble(), 4.0)
            .toFloat() else 1.0f - Math.pow(-2.0 * _value + 2.0, 4.0)
            .toFloat() / 2.0f
    }

    private fun easeInQuint(_value: Float): Float {
        return Math.pow(_value.toDouble(), 5.0).toFloat()
    }

    private fun easeOutQuint(_value: Float): Float {
        return 1.0f - Math.pow((1 - _value).toDouble(), 5.0).toFloat()
    }

    private fun easeInOutQuint(_value: Float): Float {
        return if (_value < 0.5) 16 * Math.pow(_value.toDouble(), 5.0)
            .toFloat() else 1.0f - Math.pow(-2.0 * _value + 2.0, 5.0)
            .toFloat() / 2.0f
    }

    private fun easeInExpo(_value: Float): Float {
        return if (_value == 0f) 0.0f else 2.0.pow((10 * _value - 10).toDouble()).toFloat()
    }

    private fun easeOutExpo(_value: Float): Float {
        return if (_value == 1f) 1.0f else 1 - 2.0.pow((-10 * _value).toDouble()).toFloat()
    }

    private fun easeInOutExpo(_value: Float): Float {
        return if (_value == 0.0f) 0.0f else if (_value == 1.0f) 1.0f else if (_value < 0.5f) Math.pow(
            2.0,
            20.0 * _value - 10.0
        )
            .toFloat() / 2.0f else (2.0f - Math.pow(2.0, -20.0 * _value + 10.0).toFloat()) / 2.0f
    }

    private fun easeInCirc(_value: Float): Float {
        return 1.0f - Math.sqrt(1.0 - Math.pow(_value.toDouble(), 2.0).toFloat()).toFloat()
    }

    private fun easeOutCirc(_value: Float): Float {
        return Math.sqrt(1.0 - Math.pow(_value - 1.0, 2.0)).toFloat()
    }

    private fun easeInOutCirc(_value: Float): Float {
        return if (_value < 0.5f) (1.0f - Math.sqrt(1.0 - Math.pow(2.0 * _value, 2.0))
            .toFloat()) / 2.0f else (Math.sqrt(1.0 - Math.pow(-2.0 * _value + 2.0, 2.0)).toFloat() + 1.0f) / 2.0f
    }

    private fun easeInBack(_value: Float): Float {
        return c3 * _value * _value * _value - c1 * _value * _value
    }

    private fun easeOutBack(_value: Float): Float {
        return 1 + c3 * Math.pow((_value - 1).toDouble(), 3.0).toFloat() + c1 * Math.pow((_value - 1).toDouble(), 2.0)
            .toFloat()
    }

    private fun easeInOutBack(_value: Float): Float {
        return if (_value < 0.5f) Math.pow(2.0 * _value, 2.0)
            .toFloat() * ((c2 + 1.0f) * 2.0f * _value - c2) / 2.0f else (Math.pow(2.0 * _value - 2.0, 2.0)
            .toFloat() * ((c2 + 1) * (_value * 2.0f - 2.0f) + c2) + 2.0f) / 2.0f
    }

    private fun easeInElastic(_value: Float): Float {
        return if (_value == 0f) 0.0f else if (_value == 1f) 1.0f else (-Math.pow(
            2.0,
            (10 * _value - 10).toDouble()
        )).toFloat() * Math.sin((_value * 10 - 10.75) * c4)
            .toFloat()
    }

    private fun easeOutElastic(_value: Float): Float {
        return if (_value == 0f) 0.0f else if (_value == 1f) 1.0f else Math.pow(2.0, (-10 * _value).toDouble())
            .toFloat() * Math.sin((_value * 10 - 0.75) * c4)
            .toFloat() + 1
    }

    private fun easeInOutElastic(_value: Float): Float {
        return if (_value == 0f) 0.0f else if (_value == 1f) 1.0f else if (_value < 0.5) -(Math.pow(
            2.0,
            (20 * _value - 10).toDouble()
        )
            .toFloat() * Math.sin(((20 * _value - 11.125f) * c5).toDouble())
            .toFloat()) / 2 else Math.pow(2.0, (-20 * _value + 10).toDouble())
            .toFloat() * Math.sin(((20 * _value - 11.125f) * c5).toDouble())
            .toFloat() / 2 + 1
    }

    private fun easeInBounce(_value: Float): Float {
        return 1 - easeOutBounce(1 - _value)
    }

    private fun easeOutBounce(_value: Float): Float {
        var _value = _value
        return if (_value < 1 / d1) {
            n1 * _value * _value
        } else if (_value < 2.0f / d1) {
            n1 * (1.5f / d1.let { _value -= it; _value }) * _value + 0.75f
        } else if (_value < 2.5f / d1) {
            n1 * (2.25f / d1.let { _value -= it; _value }) * _value + 0.9375f
        } else {
            (n1 * (2.625 / d1.let { _value -= it; _value }) * _value + 0.984375f).toFloat()
        }
    }

    private fun easeInOutBounce(_value: Float): Float {
        return if (_value < 0.5) (1 - easeOutBounce(1 - 2 * _value)) / 2 else (1 + easeOutBounce(2 * _value - 1)) / 2
    }
}