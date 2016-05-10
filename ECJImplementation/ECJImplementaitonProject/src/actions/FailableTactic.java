package actions;

import ec.gp.GPNode;

public abstract class FailableTactic extends GPNode {
	
	protected double failureWeight;
	//assuming action succeeded unless set otherwise
	protected boolean actionSucceeded = true;
	
	public boolean hasActionSucceeded(){
		return actionSucceeded;
	}
	
	public double getFailureWeight(){
		return failureWeight;
	}

}
