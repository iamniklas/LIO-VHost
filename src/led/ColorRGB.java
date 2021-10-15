package led;

import java.awt.Color;

import com.google.gson.annotations.SerializedName;

public class ColorRGB {
	
	@SerializedName("R")
	public int r = 255;
	
	@SerializedName("G")
	public int g = 255;
	
	@SerializedName("B")
	public int b = 255;
	
	public static final ColorRGB black = new ColorRGB(0, 0, 0);
	public static final ColorRGB white = new ColorRGB(255, 255, 255);
	public static final ColorRGB white50 = new ColorRGB(128, 128, 128);
	public static final ColorRGB red = new ColorRGB(255, 0, 0);
	public static final ColorRGB green = new ColorRGB(0, 255, 0);
	public static final ColorRGB blue = new ColorRGB(0, 0, 255);
	public static final ColorRGB orange = new ColorRGB(255, 255, 0);
	public static final ColorRGB magenta = new ColorRGB(255, 0, 255);
	public static final ColorRGB torquoise = new ColorRGB(0, 255, 255);
	
	public ColorRGB(int _r, int _g, int _b) {
		r = _r;
		g = _g;
		b = _b;
	}
	
	public ColorRGB(ColorRGB _color) {
		r = _color.r;
		g = _color.g;
		b = _color.b;
	}
	
	public ColorRGB filter(ColorChannel _channel) {
		switch (_channel) {
			case Red: return new ColorRGB(0, g, b);
			case Green: return new ColorRGB(r, 0, b);
			case Blue: return new ColorRGB(r, g, 0);
			default: return new ColorRGB(0, 0, 0);
		}
	}
	
	public void setChannel(ColorChannel _channel, int _value) {
		switch (_channel) {
			case Red:
				r = _value;
				break;
			case Green:
				g = _value;
				break;
			case Blue:
				b = _value;
				break;
		default:
			break;
		}
	}
	
	//Cuts a channel from the lower side - if the channel value is higher than the filter it will be set to 0
	public ColorRGB cutLow(ColorChannel _channel, int _filter) {
		_filter = Math.max(Math.min(_filter, 255), 0);
		switch (_channel) {
			case Red: return new ColorRGB(cutLow(r, _filter), g, b);
			case Green: return new ColorRGB(r, cutLow(g, _filter), b);
			case Blue: return new ColorRGB(r, g, cutLow(b, _filter));
			default: return this;
		}
	}
	
	//Cuts a channel from the higher side - if the channel value is lower than the filter it will be set to 0
	public ColorRGB cutHigh(ColorChannel _channel, int _filter) {
		_filter = Math.max(Math.min(_filter, 255), 0);
		switch (_channel) {
			case Red: return new ColorRGB(cutHigh(r, _filter), g, b);
			case Green: return new ColorRGB(r, cutHigh(g, _filter), b);
			case Blue: return new ColorRGB(r, g, cutHigh(b, _filter));
			default: return this;
		}
	}
	
	public ColorRGBA toRGBA(int _alpha) {
		return new ColorRGBA(r, g, b, _alpha);
	}
	
	public ColorHSV toHSV() {
		return new ColorHSV(0, 0, 0);
	}
	
	private int cutLow(int _value, int _filter) {
		if (_value >= _filter) {
			return 0;
		}
		else {
			return _value;
		}
	}
	
	private int cutHigh(int _value, int _filter) {
		if (_value <= _filter) {
			return 0;
		}
		else {
			return _value;
		}
	}
	
	public ColorRGB dim(float _percentage) {
		return new ColorRGB(
				(int)(r * _percentage), 
				(int)(g * _percentage), 
				(int)(b * _percentage));
	}
	
	public ColorRGB dim(float _percentageR, float _percentageG, float _percentageB) {
		return new ColorRGB(
				(int)(r * _percentageR), 
				(int)(g * _percentageG), 
				(int)(b * _percentageB));
	}
	
	public static ColorRGB fromSystemColor(Color _systemColor) {
		return new ColorRGB(_systemColor.getRed(), _systemColor.getGreen(), _systemColor.getBlue());
	}
	
	public Color toSystemColor() {
		return new Color(r, g, b);
	}
	
	@Override
	public String toString() {
		return String.format("R%d G%d B%d", r, g, b);
	}
}