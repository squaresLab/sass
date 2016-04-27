package actions.tactics;

import main.StateData;

public abstract class IncreaseDatabaseThreads extends ActionTemplate{

	@Override
	boolean areAddingItem() {
		return true;
	}
	
	@Override
	public String toString() {
		return "IncreaseDatabaseThreads";
	}

}
