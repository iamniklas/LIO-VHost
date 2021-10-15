package procedures;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import led.LEDDataBundle;
import led.json.LEDJsonProcedure;
import led.json.interpreter.FileVersions;
import led.json.interpreter.LEDInterpreter;

public class JsonProcedure extends Procedure {

	LEDJsonProcedure mLEDJsonProcedure;
	
	public JsonProcedure(LEDDataBundle _bundle) {
		super(_bundle);

		mLEDJsonProcedure = loadFromFile(_bundle.path);
		mSteps = mLEDJsonProcedure.mLEDStates.length - 1;
		
		//mIsSubProcedure = (boolean) _bundle.get(ProcedureBundleFields.IS_SUB_PROCEDURE);
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	void update() {
		for (int i = 0; i < mLEDJsonProcedure.mMetaInfo.mLedCount; i++) {
			mStrip.setPixel(i, mLEDJsonProcedure.mLEDStates[mStep].mLEDState[i].toSystemColor());
		}
		
		mStep+=5;
		
		if (mStep >= mSteps) {
			finishProcedure();
		}
	}
	
	private LEDJsonProcedure loadFromFile(String _path) {
		  byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(_path));
			return LEDInterpreter.interpretJson(FileVersions.V0_0_1, new String(encoded, StandardCharsets.US_ASCII));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}