package main.java.actions;

import ec.gp.GPNode;
import main.java.main.OmnetStatePath;

public abstract class FailableTactic extends GPNode {
	
	protected double failureWeight;
	//assuming action succeeded unless set otherwise
	protected boolean actionSucceeded = true;
	
	protected boolean undoSucceeded = true;
	
	public boolean hasActionSucceeded(){
		return actionSucceeded;
	}
	
	public void setActionSucceeded(boolean actionSucceeded){
		this.actionSucceeded = actionSucceeded;
	}
	
	public boolean hasUndoSucceeded(){
		return undoSucceeded;
	}
	
	public void setUndoSucceeded(boolean undoSucceeded){
		this.undoSucceeded = undoSucceeded;
	}
	public double getFailureWeight(){
		return failureWeight;
	}

	

}
