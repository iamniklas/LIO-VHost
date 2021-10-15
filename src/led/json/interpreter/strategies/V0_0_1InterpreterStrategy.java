package led.json.interpreter.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;

import led.ColorRGB;
import led.json.LEDJsonProcedure;
import led.json.LEDStateArray;

public class V0_0_1InterpreterStrategy implements IInterpreterStrategy {

	@Override
	public ColorRGB[] interpretLine(String _line) {
		String[] colorData = _line.split(" ");
		
		ArrayList<ColorRGB> colors = new ArrayList<ColorRGB>();
		for (int i = 0; i < colorData.length; i++) {
			String[] ledData = colorData[i].split("-");
			
			int[] arr = Stream.of(ledData).mapToInt(Integer::parseInt).toArray();
			
			colors.add(new ColorRGB(arr[0], arr[1], arr[2]));
		}
		
		return colors.toArray(new ColorRGB[colors.size()]);
	}

	@Override
	public LEDJsonProcedure interpretJson(String _json) {
		LEDJsonProcedure001Uncompiled jsonProc = new Gson().fromJson(_json, LEDJsonProcedure001Uncompiled.class);
		LEDJsonProcedure result = new LEDJsonProcedure();
		result.mMetaInfo = jsonProc.mMetaInfo;
		List<LEDStateArray> l = new ArrayList<LEDStateArray>();
		for (int i = 0; i < 300; i++) {
			l.add(new LEDStateArray());
			l.get(i).mLEDState = interpretLine(jsonProc.mLEDStates[i]);
		}
		result.mLEDStates = new LEDStateArray[300];
		l.toArray(result.mLEDStates);
		System.out.println(result.mLEDStates.length);
		return result;
	}

}
