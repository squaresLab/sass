package main.java.znn.tactics;


public abstract class DecreaseDatabaseThreads extends main.java.actions.ActionTemplate{
	

	@Override
	protected boolean areAddingItem() {
		return false;
	}
	
	@Override
	public String toString() {
		return "DecreaseDatabaseThreads";
	}


}
