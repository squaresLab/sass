package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerF;

public class StartNewServerF extends StartNewServer {

	public StartNewServerF(){
		latency=240;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "StartNewServerF";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerF.class);
		
	}


	

}

