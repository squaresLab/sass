package tactics;

import java.io.Serializable;

import system.SystemState;

public abstract class Tactic implements Cloneable, Serializable {
	
	Long startTime = null;
	
	// apply the changes to the system state
	public abstract void visit(SystemState systemState);

	// remove the changes from the system state
	public abstract void undo(SystemState systemState);
	
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	};

	public abstract int size();

	public abstract long getExecutionTime();

	public void setStartTime(long time){
		this.startTime = time;
	}
	
	public Long getStartTime(){
		return this.startTime;
	}
	
	public Long getEndTime(){
		if (startTime == null){
			return null;
		}
		return startTime + getExecutionTime();
	}

}
