package znn.tactics;

import actions.ActionTemplate;
import main.StateData;

public abstract class IncreaseDatabaseThreads extends ActionTemplate{

	@Override
	protected boolean areAddingItem() {
		return true;
	}
	
	@Override
	public String toString() {
		return "IncreaseDatabaseThreads";
	}

}
