package znn.components;

import main.StateData;

public class DatabaseAThread extends DatabaseThread{
	
	public DatabaseAThread(){
    	clockTime=2;
        failureWeight=0.01;
        cost=0;
	}

	@Override
	public double getComputingPowerChange(StateData sd) {
		return 3-sd.getDatabaseAThreadsCount()*.1;
	}
	

	public double getFirstThreadComputingPowerChange(){
		return 3;
	}
	
	public double getSecondThreadComputingPowerChange(){
		return (3-0.1);
	}
	
}
