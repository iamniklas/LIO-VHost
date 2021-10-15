package led.json.interpreter.strategies;

import led.ColorRGB;
import led.json.LEDJsonProcedure;

public interface IInterpreterStrategy {
	ColorRGB[] interpretLine(String _line);
	LEDJsonProcedure interpretJson(String _json);
}