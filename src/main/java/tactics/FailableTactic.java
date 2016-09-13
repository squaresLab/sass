package tactics;

public abstract class FailableTactic implements Tactic {
	
	private boolean failed;
	
	public FailableTactic(){
		failed = false;
	}
	
	public boolean getFailed(){
		return failed;
	}
	
	public void setFailed(boolean failstate){
		failed = failstate;
	}
	
	public abstract double getFailChance();
	
	// get a failed tactic
	public Tactic getFail() {
		
		FailableTactic copy = (FailableTactic) clone();
			
			copy.setFailed(true);
			
			return copy;
	
		
	}
	
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
