package znn.tactics;


public abstract class DecreaseDatabaseThreads extends actions.ActionTemplate{
	

	@Override
	protected boolean areAddingItem() {
		return false;
	}
	
	@Override
	public String toString() {
		return "DecreaseDatabaseThreads";
	}


}
