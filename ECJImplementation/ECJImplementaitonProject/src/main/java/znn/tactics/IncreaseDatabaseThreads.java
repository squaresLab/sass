package main.java.znn.tactics;

public abstract class IncreaseDatabaseThreads extends main.java.actions.ActionTemplate{

	@Override
	protected boolean areAddingItem() {
		return true;
	}
	
	@Override
	public String toString() {
		return "IncreaseDatabaseThreads";
	}

}
