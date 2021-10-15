package led;

public class ColorHSV {
	public int h;
	public float s;
	public float v;
	
	public ColorHSV(int _h, float _s, float _v) {
		h = _h;
		s = _s;
		v = _v;
	}
	
	public ColorRGB ToRGB() {
		int v255 = (int) (v * 255.0f);
		if(s == 0.0f) { return new ColorRGB(v255, v255, v255); }
		
		int hueInterval = (int) Math.floor(h / 60.0f);
        float f = (h / 60.0f) - (float) hueInterval;
        
        int p = (int) ((v * (1 - s)) * 255.0f);
        int q = (int) ((v * (1 - s * f)) * 255.0f);
        int t = (int) ((v * (1 - s * (1 - f))) * 255.0f);
        
        ColorRGB result = 
        		hueInterval == 0 ||
        		hueInterval == 6 ? 	new ColorRGB(v255, t, p) :
    			hueInterval == 1 ? 	new ColorRGB(q, v255, p) :
				hueInterval == 2 ? 	new ColorRGB(p, v255, t) :
				hueInterval == 3 ? 	new ColorRGB(p, q, v255) :
				hueInterval == 4 ? 	new ColorRGB(t, p, v255) :
				hueInterval == 5 ? 	new ColorRGB(v255, p, q) : 
								   	new ColorRGB(0, 0, 0);
        
        return result;
	}
}