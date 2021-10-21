package procedures;

import led.LEDDataBundle;

public class ProcedureFactory {
	public static Procedure getProcedure(ProcedureType _types, LEDDataBundle _bundle) {
		switch(_types) {
		case BootComplete: 		 return new BootCompleteProcedure(_bundle);
		case ColorInstantSet: 	 return new ColorInstantSetProcedure(_bundle); 
		case FadeInFadeOut: 	 return new FadeInFadeOutProcedure(_bundle);
		case FadeToMultiColor: 	 return new FadeToMultiColorProcedure(_bundle);
		case FadeToUniformColor: return new FadeToUniformColorProcedure(_bundle);
		case Fill: 				 return new FillStripProcedure(_bundle);
		case FillInterpolated: 	 return new FillStripInterpolatedProcedure(_bundle);
		case Rainbow:			 return new RainbowProcedure(_bundle);
		case RainbowMono:		 return new RainbowMonoProcedure(_bundle);
		case SimpleBPM:			 return new SimpleBPMProcedure(_bundle);
		case MultiProcedure:	 return new MultiProcedure(_bundle);
		case Blink:	 			 return new BlinkProcedure(_bundle);
		case Glitter:			 return new GlitterProcedure(_bundle);
		case Navigation: 		 return new NavigationProcedure(_bundle);
		case Progress: 			 return new ProgressProcedure(_bundle);
		case Lightning:			 return new LightningProcedure(_bundle);
		case NoLongerReady:		 return new NoLongerReadyProcedure(_bundle);
		case JsonProcedure:		 return new JsonProcedure(_bundle);
		case Javascript:		 		 return new JavascriptProcedure(_bundle);
		default: 				 return null;
		}
	}
}