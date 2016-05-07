package znn.tactics;

import actions.ActionTemplate;
import main.StateData;

public abstract class DecreaseDatabaseThreads extends ActionTemplate{
	

	@Override
	protected boolean areAddingItem() {
		return false;
	}
	
	@Override
	public String toString() {
		return "DecreaseDatabaseThreads";
	}


}
