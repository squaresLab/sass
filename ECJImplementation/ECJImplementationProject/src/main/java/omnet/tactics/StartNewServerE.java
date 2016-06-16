package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerE;

public class StartNewServerE extends StartNewServer {

	public StartNewServerE(){
		latency=180;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "StartNewServerE";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerE.class);
		
	}


	

}

