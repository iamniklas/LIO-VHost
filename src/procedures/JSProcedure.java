package procedures;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import led.LEDDataBundle;

public class JSProcedure extends Procedure {

	String jsStart = "";
	StringBuilder jsUpdate = new StringBuilder();
	
    ScriptEngine runtime = null;
    Invocable invokeEngine = null;
	Object eval = null;
    
	public JSProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		jsUpdate.append("indices = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 299];");
		
		runtime = new ScriptEngineManager().getEngineByName("javascript");
		
		jsUpdate.append("function update(step) {");
		jsUpdate.append("d = Math.abs(Math.sin(step * Math.PI/180));");
		jsUpdate.append("return d");
		jsUpdate.append("}");
		
		try {
			eval = runtime.eval(jsUpdate.toString());
			invokeEngine = (Invocable) runtime;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		
	}

	@Override
	void update() {
        try {
			Double o = (Double) invokeEngine.invokeFunction("update", mStep);
			mStep++;
			System.out.println(o);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}