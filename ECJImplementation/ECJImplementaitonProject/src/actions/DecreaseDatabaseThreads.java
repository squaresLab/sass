package actions;

import main.StateData;

public abstract class DecreaseDatabaseThreads extends ActionTemplate{
	

	@Override
	boolean areAddingItem() {
		return false;
	}
	
	@Override
	public String toString() {
		return "DecreaseDatabaseThreads";
	}


}
