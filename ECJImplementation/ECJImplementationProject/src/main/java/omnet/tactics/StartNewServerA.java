package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerA;

public class StartNewServerA extends StartNewServer {

	public StartNewServerA(){
		latency = 120;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "StartNewServerA";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}


	

}

