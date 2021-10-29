package led;

import com.github.iamniklas.liocore.led.LEDRenderer;

import javax.swing.*;

public class SwingRenderer extends LEDRenderer {

    private JLabel[] leds;

    public SwingRenderer(JLabel[] uiLeds, int _stripSize) {
        super(_stripSize);
        leds = uiLeds;
    }

    @Override
    public void render() {
        for (int i = 0; i < leds.length; i++) {
            leds[i].setForeground(colorData[i]);
        }
    }
}
