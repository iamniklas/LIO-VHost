import com.github.iamniklas.colorspaces.ColorRGB;
import com.github.iamniklas.liocore.led.LEDDataBundle;
import com.github.iamniklas.liocore.led.colorspace.LIOColor;
import com.github.iamniklas.liocore.procedures.Procedure;

import java.awt.*;

enum BPMProcedureState { INCREASE, DECREASE }

public class AdvBPMProcedure extends Procedure  {

    private final int beatsPerTact = 3;
    private BPMProcedureState procedureState;
    private int currentBeat = 0;

    private int testCounter = 0;

    public AdvBPMProcedure(LEDDataBundle _bundle) {
        super(_bundle);
    }

    @Override
    public void update() {
        strip.setAllPixels(LIOColor.fromRGB(ColorRGB.BLACK));

        strip.setArea(currentBeat * 75, (currentBeat+1) * 75, LIOColor.fromRGB(new ColorRGB(Math.round(255 * (testCounter / 30f)), 0, 0)));

        strip.setPixel(currentBeat * 75, LIOColor.fromRGB(ColorRGB.WHITE));
        strip.setPixel(Math.min((currentBeat+1) * 75, 299), LIOColor.fromRGB(ColorRGB.WHITE));

        testCounter++;

        if(testCounter > 30) {

            testCounter = 0;

            if(currentBeat == beatsPerTact) {
                currentBeat = 0;
            } else {
                currentBeat++;
            }
        }
    }

    @Override
    public void updateLEDDataBundle(LEDDataBundle bundle) {

    }
}

