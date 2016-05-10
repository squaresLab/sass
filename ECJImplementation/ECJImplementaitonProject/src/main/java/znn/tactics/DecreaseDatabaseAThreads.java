package main.java.znn.tactics;

import main.java.main.StateData;
import main.java.znn.components.DatabaseAThread;

public class DecreaseDatabaseAThreads extends DecreaseDatabaseThreads {

	
	public DecreaseDatabaseAThreads(){
		component=new DatabaseAThread();
	}
	
	@Override
	public String toString() {
		return "DecreaseDatabaseAThreads";
	}

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setDatabaseAThreadsCount(sd.getDatabaseAThreadsCount()-1);
		return sd.getDatabaseAThreadsCount() < 1; 
	}



	

}
