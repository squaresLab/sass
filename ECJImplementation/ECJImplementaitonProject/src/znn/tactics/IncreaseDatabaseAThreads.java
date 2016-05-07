package znn.tactics;

import main.StateData;
import znn.components.DatabaseAThread;

public class IncreaseDatabaseAThreads extends IncreaseDatabaseThreads{

	
    public IncreaseDatabaseAThreads(){
       component=new DatabaseAThread();
    }	
	
	@Override
	public String toString() {
		return "IncreaseDatabaseAThreads";
	}

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setDatabaseAThreadsCount(sd.getDatabaseAThreadsCount()+1);
		return sd.getDatabaseAThreadsCount() > sd.getMaxDatabaseAThreadsCount(); 
	}


	
}
