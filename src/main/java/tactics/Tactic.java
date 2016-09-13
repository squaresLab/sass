package tactics;

import system.SystemState;

public interface Tactic extends Cloneable {
	
	// apply the changes to the system state
	public void visit(SystemState systemState);

	// remove the changes from the system state
	public void undo(SystemState systemState);
	
	public Object clone();

}
