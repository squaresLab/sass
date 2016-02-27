package actions;

import components.DatabaseAThread;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Parameter;
import main.StateData;

public class DecreaseDatabaseAThreads extends DecreaseDatabaseThreads {

	
	public DecreaseDatabaseAThreads(){
		component=new DatabaseAThread();
	}
	
	@Override
	public String toString() {
		return "DecreaseDatabaseAThreads";
	}

	@Override
	protected boolean invalidChangeAtLocation(StateData sd) {
		sd.setDatabaseAThreadsCount(sd.getDatabaseAThreadsCount()-1);
		return sd.getDatabaseAThreadsCount() < 1; 
	}



	

}
