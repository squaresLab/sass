package main.java.znn.tactics;

import main.java.main.StateData;
import main.java.znn.components.DatabaseBThread;

public class IncreaseDatabaseBThreads extends IncreaseDatabaseThreads {

	
	public IncreaseDatabaseBThreads(){
		component=new DatabaseBThread();
	}
	
	@Override
	public String toString() {
		return "IncreaseDatabaseBThreads";
	}

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setDatabaseBThreadsCount(sd.getDatabaseBThreadsCount()+1);
		return sd.getDatabaseBThreadsCount() > sd.getMaxDatabaseBThreadsCount(); 
	}

}
