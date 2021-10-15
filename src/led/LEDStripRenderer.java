package led;

import java.awt.Color;

import javax.swing.JLabel;

public class LEDStripRenderer {

	private Color[] colorData = new Color[300];
	public static JLabel[] leds;
	
	
	
	public void setPixel(int _index, Color _color) {
		colorData[_index] = _color;
	}
	
	public void render() {
		for (int i = 0; i < leds.length; i++) {
			leds[i].setForeground(colorData[i]);
		}
	}
}