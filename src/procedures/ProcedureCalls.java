package procedures;

public interface ProcedureCalls {
	public void onProcedureStart(Procedure _procedure);
	public void onProcedureQueued();
	public void onProcedureFinish();
}