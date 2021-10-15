package interpolation;

public class Interpolation {

	private final static float c1 = 1.70158f;
	private final static float c2 = c1 * 1.525f;
	private final static float c3 = c1 + 1.0f;
	private final static float c4 = (float) (2 * Math.PI) / 3.0f;
	private final static float c5 = (float) (2 * Math.PI) / 4.5f;
	
	private final static float n1 = 7.5625f;
	private final static float d1 = 2.75f;
	
	public static float getInterpolationValue(float _value, InterpolationType _interpolationType) {
		switch (_interpolationType) {
		case EaseInSine: return easeInSine(_value);
		case EaseOutSine: return easeOutSine(_value);
		case EaseInOutSine: return easeInOutSine(_value);
			
		case EaseInQuad: return easeInQuad(_value);
		case EaseOutQuad: return easeOutQuad(_value);
		case EaseInOutQuad: return easeInOutQuad(_value);
			
		case EaseInCubic: return easeInCubic(_value);
		case EaseOutCubic: return easeOutCubic(_value);
		case EaseInOutCubic: return easeInOutCubic(_value);
		
		case EaseInQuart: return easeInQuart(_value);
		case EaseOutQuart: return easeOutQuart(_value);
		case EaseInOutQuart: return easeInOutQuart(_value);
			
		case EaseInQuint: return easeInQuint(_value);
		case EaseOutQuint: return easeOutQuint(_value);
		case EaseInOutQuint: return easeInOutQuint(_value);
			
		case EaseInExpo: return easeInExpo(_value);
		case EaseOutExpo: return easeOutExpo(_value);			 
		case EaseInOutExpo: return easeInOutExpo(_value);
			
		case EaseInCirc: return easeInCirc(_value);
		case EaseOutCirc: return easeOutCirc(_value);
		case EaseInOutCirc:	return easeInOutCirc(_value);		
			
		case EaseInBack: return easeInBack(_value);
		case EaseOutBack: return easeOutBack(_value);
		case EaseInOutBack: return easeInOutBack(_value);
			
		case EaseInElastic: return easeInElastic(_value);
		case EaseOutElastic: return easeOutElastic(_value);
		case EaseInOutElastic: return easeInOutElastic(_value);
			
		case EaseInBounce: return easeInBounce(_value);
		case EaseOutBounce: return easeOutBounce(_value);
		case EaseInOutBounce: return easeInOutBounce(_value);
		
		case Linear:
		default: return _value;
		}
		
	}
	
	private static float easeInSine(float _value) { 
		return 1.0f - (float) Math.cos((_value * Math.PI) / 2.0f); 
	}
	private static float easeOutSine(float _value) { 
		return (float) Math.sin((_value * Math.PI) / 2.0f); 
	}
	private static float easeInOutSine(float _value) { 
		return (float) -(Math.cos(Math.PI * _value) - 1.0f) / 2.0f; 
	}
	
	private static float easeInQuad(float _value) { 
		return (float) Math.pow(_value, 2.0); 
	}
	private static float easeOutQuad(float _value) { 
		return 1 - (1 - _value) * (1 - _value); 
	}
	private static float easeInOutQuad(float _value) { 
		return _value < 0.5f ? 
				2 * _value * _value : 
				1 - (float) Math.pow(-2.0 * _value + 2.0, 2.0) / 2; 
	}
	
	private static float easeInCubic(float _value) { 
		return (float) Math.pow(_value, 3.0); 
	}
	private static float easeOutCubic(float _value) { 
		return 1.0f - (float) Math.pow(1.0 - _value, 3.0); 
	}
	private static float easeInOutCubic(float _value) { 
		return _value < 0.5f ? 
				4.0f * _value * _value * _value : 
				1.0f - (float) Math.pow(-2.0 * _value + 2.0, 3.0) / 2.0f; 
	}
	
	private static float easeInQuart(float _value) { 
		return (float) Math.pow(_value, 4.0); 
	}
	private static float easeOutQuart(float _value) { 
		return 1.0f - (float) Math.pow(1 - _value, 4.0); 
	}
	private static float easeInOutQuart(float _value) { 
		return _value < 0.5f ? 
				8 * (float)Math.pow(_value, 4.0) : 
				1.0f - (float) Math.pow(-2.0 * _value + 2.0, 4.0) / 2.0f; 
	}
	
	private static float easeInQuint(float _value) { 
		return (float) Math.pow(_value, 5); 
	}
	private static float easeOutQuint(float _value) { 
		return 1.0f - (float) Math.pow(1 - _value, 5); 
	}
	private static float easeInOutQuint(float _value) { 
		return _value < 0.5 ? 
				16 * (float) Math.pow(_value, 5.0f) : 
				1.0f - (float)Math.pow(-2.0 * _value + 2.0, 5.0) / 2.0f; 
	}
	
	private static float easeInExpo(float _value) { 
		return _value == 0 ? 
				0 : 
				(float)Math.pow(2, 10 * _value - 10); 
	}
	private static float easeOutExpo(float _value) { 
		return _value == 1 ? 
				1 : 
				1 - (float)Math.pow(2, -10 * _value); 
	}
	private static float easeInOutExpo(float _value) { 
		return _value == 0.0f ? 
			0.0f : 
			_value == 1.0f ? 
			1.0f : 
			_value < 0.5f ? 
			(float)Math.pow(2.0, 20.0 * _value - 10.0) / 2.0f : 
			(2.0f - (float)Math.pow(2.0, -20.0 * _value + 10.0)) / 2.0f; 
	}
	
	private static float easeInCirc(float _value) { 
		return 1.0f - (float)Math.sqrt(1.0 - (float)Math.pow(_value, 2.0)); 
	}
	private static float easeOutCirc(float _value) { 
		return (float)Math.sqrt(1.0 - Math.pow(_value - 1.0, 2.0)); 
	}
	private static float easeInOutCirc(float _value) { 
		return _value < 0.5f ? 
		(1.0f - (float) Math.sqrt(1.0 - Math.pow(2.0 * _value, 2.0))) / 2.0f : 
		((float)Math.sqrt(1.0 - Math.pow(-2.0 * _value + 2.0, 2.0)) + 1.0f) / 2.0f; 
	}
	
	private static float easeInBack(float _value) { 
		return c3 * _value * _value * _value - c1 * _value * _value; 
	}
	private static float easeOutBack(float _value) { 
		return 1 + c3 * (float)Math.pow(_value - 1, 3) + c1 * (float)Math.pow(_value - 1, 2); 
	}
	private static float easeInOutBack(float _value) { 
		return _value < 0.5f ? 
				((float)Math.pow(2.0 * _value, 2.0) * ((c2 + 1.0f) * 2.0f * _value - c2)) / 2.0f : 
				((float)Math.pow(2.0 * _value - 2.0, 2.0) * ((c2 + 1) * (_value * 2.0f - 2.0f) + c2) + 2.0f) / 2.0f; 
	}
	
	private static float easeInElastic(float _value) { 
		return _value == 0 ? 
				0 : _value == 1 ? 
				1 : -(float)Math.pow(2, 10 * _value - 10) * (float)Math.sin((_value * 10 - 10.75) * c4); 
	}
	private static float easeOutElastic(float _value) { 
		return _value == 0 ? 
				0 : _value == 1 ? 
				1 : (float)Math.pow(2, -10 * _value) * (float)Math.sin((_value * 10 - 0.75) * c4) + 1; 
	}
	private static float easeInOutElastic(float _value) { 
		return _value == 0 ? 
				0 : _value == 1 ? 
				1 : _value < 0.5 ? 
						-((float)Math.pow(2, 20 * _value - 10) * (float)Math.sin((20 * _value - 11.125f) * c5)) / 2 : 
						((float)Math.pow(2, -20 * _value + 10) * (float)Math.sin((20 * _value - 11.125f) * c5)) / 2 + 1; 
	}
	
	private static float easeInBounce(float _value) { 
		return 1 - easeOutBounce(1 - _value); 
	}
	private static float easeOutBounce(float _value) { 
		if (_value < 1 / d1) {
			return n1 * _value * _value;
		} else if (_value < 2.0f / d1) {
			return n1 * (_value -= 1.5f / d1) * _value + 0.75f;
		} else if (_value < 2.5f / d1) {
			return n1 * (_value -= 2.25f / d1) * _value + 0.9375f;
		} else {
			return n1 * (_value -= 2.625 / d1) * _value + 0.984375f;
		} 
	}
	private static float easeInOutBounce(float _value) 	{ 
		return _value < 0.5 ? 
				(1 - easeOutBounce(1 - 2 * _value)) / 2 : 
				(1 + easeOutBounce(2 * _value - 1)) / 2; 
	}
}