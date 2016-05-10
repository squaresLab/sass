package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerB;

public class StartNewServerB extends StartNewServer {

	public StartNewServerB(){
		latency = 120;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "StartNewServerB";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);
		
	}


	

}

