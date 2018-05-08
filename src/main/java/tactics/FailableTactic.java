package tactics;

public abstract class FailableTactic extends Tactic {
	
	private boolean failed;
	
	private boolean intentionalFail;

	private Long startTime;
	
	public FailableTactic(){
		startTime = null;
		failed = false;
	}
	
	public boolean getFailed(){
		return failed;
	}
	
	public boolean getIntentionalFailed(){
		return intentionalFail;
	}
	
	public void setFailed(boolean failstate){
		failed = failstate;
	}
	
	public void setStartTime(long time){
		this.startTime = time;
	}
	
	public Long getStartTime(){
		return startTime;
	}
	
	public abstract double getFailChance();
	
	// get a failed tactic
	public FailableTactic getFail() {
		
		FailableTactic copy = (FailableTactic) clone();
			
			copy.setFailed(true);
			copy.intentionalFail = true;
			
			return copy;
	
	}
	
	// default size is 1
	public int size(){
		return 1;
	}

}
