package led;

public class ColorRGBA {
	int r = 255;
	int g = 255;
	int b = 255;
	int a = 255;
	
	public ColorRGBA(int _r, int _g, int _b, int _a) {
		r = _r;
		g = _g;
		b = _b;
		a = _a;
	}
	
	public ColorRGB toRGB() {
		return new ColorRGB(
				(255 - a) * ColorRGB.black.r + a * r,
		        (255 - a) * ColorRGB.black.g + a * g,
		        (255 - a) * ColorRGB.black.b + a * b);
	}
	
	public ColorRGB toRGB(ColorRGB _baseColor) {
		return new ColorRGB(
				(int)((1 - a/255.0f) * _baseColor.r + (a/255.0f) * r),
				(int)((1 - a/255.0f) * _baseColor.g + (a/255.0f) * g),
				(int)((1 - a/255.0f) * _baseColor.b + (a/255.0f) * b));
	}
	
	public ColorRGBA dim(float _percentage) {
		return new ColorRGBA(
				(int)(r * _percentage), 
				(int)(g * _percentage), 
				(int)(b * _percentage),
				(int)(a * _percentage));
	}
	
	public ColorRGBA dim(float _percentageR, float _percentageG, float _percentageB, float _percentageA) {
		return new ColorRGBA(
				(int)(r * _percentageR), 
				(int)(g * _percentageG), 
				(int)(b * _percentageB),
				(int)(a * _percentageA));
	}
	
	@Override
	public String toString() {
		return String.format("R%d G%d B%d A%d", r, g, b, a);
	}
}