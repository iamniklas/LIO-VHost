package led.json.interpreter;

import led.ColorRGB;
import led.json.LEDJsonProcedure;
import led.json.interpreter.strategies.*;

public class LEDInterpreter {
	public static ColorRGB[] interpretLine(FileVersions _fileVersion, String _line) throws NotAvailableException {
		switch (_fileVersion) {
		case V0_0_0: throw new NotAvailableException("V0_0_0Interpreter cannot interpret lines");
		case V0_0_1: return new V0_0_1InterpreterStrategy().interpretLine(_line);
		case V0_0_2: return new V0_0_2InterpreterStrategy().interpretLine(_line);
		default: return null; 
		}
	}
	
	public static LEDJsonProcedure interpretJson(FileVersions _fileVersion, String _json) {
		switch (_fileVersion) {
		case V0_0_0: return new V0_0_0InterpreterStrategy().interpretJson(_json); 
		case V0_0_1: return new V0_0_1InterpreterStrategy().interpretJson(_json);
		case V0_0_2: return new V0_0_2InterpreterStrategy().interpretJson(_json);
		default: return null; 
		}
	}
}