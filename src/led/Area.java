package led;

public class Area {
	int mStartIndex;
	int mEndIndex;
	
	public Area(int _start, int _end) {
		mStartIndex = _start;
		mEndIndex = _end;
	}
	
	public Area(Area _area) {
		mStartIndex = _area.mStartIndex;
		mEndIndex = _area.mEndIndex;
	}
	
	public void redefineArea(int _start, int _end) {
		mStartIndex = _start;
		mEndIndex = _end;
	}
	
	public int getStartIndex() { return mStartIndex; }
	public int getEndIndex() { return mEndIndex; }
}