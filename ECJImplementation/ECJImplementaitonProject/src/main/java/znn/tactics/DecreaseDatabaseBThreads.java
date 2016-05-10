package main.java.znn.tactics;

import main.java.main.StateData;
import main.java.znn.components.DatabaseBThread;

public class DecreaseDatabaseBThreads extends DecreaseDatabaseThreads {
	
	
	public DecreaseDatabaseBThreads(){
		component=new DatabaseBThread();
	}
	
	@Override
	public String toString() {
		return "DecreaseDatabaseBThreads";
	}

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setDatabaseBThreadsCount(sd.getDatabaseBThreadsCount()-1);
		return sd.getDatabaseBThreadsCount() < 1; 
	}
}
