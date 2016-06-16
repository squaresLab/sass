package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerG;

public class StartNewServerG extends StartNewServer {

	public StartNewServerG(){
		latency=130;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "StartNewServerG";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerG.class);
		
	}


	

}

