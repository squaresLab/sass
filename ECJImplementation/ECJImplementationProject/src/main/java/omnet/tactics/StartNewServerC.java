package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerC;

public class StartNewServerC extends StartNewServer {

	public StartNewServerC(){
		latency=120;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "StartNewServerC";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}


	

}

