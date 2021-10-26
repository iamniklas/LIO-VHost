package procedures

import led.LEDDataBundle

object ProcedureFactory {
    fun getProcedure(_types: ProcedureType?, _bundle: LEDDataBundle): Procedure? {
        return when (_types) {
            ProcedureType.BootComplete -> BootCompleteProcedure(_bundle)
            ProcedureType.ColorInstantSet -> ColorInstantSetProcedure(_bundle)
            ProcedureType.FadeInFadeOut -> FadeInFadeOutProcedure(_bundle)
            ProcedureType.FadeToMultiColor -> FadeToMultiColorProcedure(_bundle)
            ProcedureType.FadeToUniformColor -> FadeToUniformColorProcedure(_bundle)
            ProcedureType.Fill -> FillStripProcedure(_bundle)
            ProcedureType.FillInterpolated -> FillStripInterpolatedProcedure(_bundle)
            ProcedureType.Rainbow -> RainbowProcedure(_bundle)
            ProcedureType.RainbowMono -> RainbowMonoProcedure(_bundle)
            ProcedureType.SimpleBPM -> SimpleBPMProcedure(_bundle)
            ProcedureType.MultiProcedure -> MultiProcedure(_bundle)
            ProcedureType.Blink -> BlinkProcedure(_bundle)
            ProcedureType.Glitter -> GlitterProcedure(_bundle)
            ProcedureType.Navigation -> NavigationProcedure(_bundle)
            ProcedureType.Progress -> ProgressProcedure(_bundle)
            ProcedureType.Lightning -> LightningProcedure(_bundle)
            ProcedureType.NoLongerReady -> NoLongerReadyProcedure(_bundle)
            ProcedureType.JsonProcedure -> JsonProcedure(_bundle)
            ProcedureType.Javascript -> JavascriptProcedure(_bundle)
            else -> null
        }
    }
}