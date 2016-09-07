package znn.tactics;

public abstract class IncreaseDatabaseThreads extends actions.ActionTemplate{

	@Override
	protected boolean areAddingItem() {
		return true;
	}
	
	@Override
	public String toString() {
		return "IncreaseDatabaseThreads";
	}

}
