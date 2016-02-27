package actions;

import components.DatabaseBThread;
import main.StateData;

public class IncreaseDatabaseBThreads extends IncreaseDatabaseThreads {

	
	public IncreaseDatabaseBThreads(){
		component=new DatabaseBThread();
	}
	
	@Override
	public String toString() {
		return "IncreaseDatabaseBThreads";
	}

	@Override
	protected boolean invalidChangeAtLocation(StateData sd) {
		sd.setDatabaseBThreadsCount(sd.getDatabaseBThreadsCount()+1);
		return sd.getDatabaseBThreadsCount() > sd.getMaxDatabaseBThreadsCount(); 
	}

}
