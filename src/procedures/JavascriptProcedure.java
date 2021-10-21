package procedures;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import led.LEDDataBundle;

public class JavascriptProcedure extends Procedure {

	String jsStart = "";
	String jsUpdate;
	
    ScriptEngine runtime = null;
    Invocable invokeEngine = null;
	Object eval = null;
    
	public JavascriptProcedure(LEDDataBundle _bundle) {
		super(_bundle);
		
		runtime = null;
		invokeEngine = null;
		eval = null;
		
		runtime = new ScriptEngineManager().getEngineByName("javascript");
		
		jsUpdate = _bundle.data;
		
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
			invokeEngine.invokeFunction("update", mStrip, this, mStep, 720);
			mStep+=5;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void finish() {
		finishProcedure();
	}
}