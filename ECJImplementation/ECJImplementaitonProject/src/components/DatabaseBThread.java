package components;

import main.StateData;

public class DatabaseBThread extends DatabaseThread{
	
	public DatabaseBThread(){
    	clockTime=2;
        failureWeight=0.01;
        cost=0;
	}
	
	@Override
	public double getComputingPowerChange(StateData sd) {
		return 3.5-sd.getDatabaseAThreadsCount()*.2;
	}
	
	public double getFirstThreadComputingPowerChange(){
		return 3.5;
	}
	
	public double getSecondThreadComputingPowerChange(){
		return (3.5-0.2);
	}
}
