package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerD;

public class StartNewServerD extends StartNewServer {

	public StartNewServerD(){
		latency=60;
		failureWeight=0.1;
	}
	
	
	@Override
	public String toString(){
	 	return "StartNewServerD";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerD.class);
		
	}


	

}

