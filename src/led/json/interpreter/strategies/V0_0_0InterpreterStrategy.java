package led.json.interpreter.strategies;

import com.google.gson.Gson;

import led.ColorRGB;
import led.json.LEDJsonProcedure;

public class V0_0_0InterpreterStrategy implements IInterpreterStrategy {
	@Override
	public ColorRGB[] interpretLine(String _line) {
		return null;
	}
	
	@Override
	public LEDJsonProcedure interpretJson(String _json) {
		return new Gson().fromJson(_json, LEDJsonProcedure.class);
	}
}