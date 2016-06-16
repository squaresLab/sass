package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerB;

public class ShutdownServerB extends ShutdownServer {

	public ShutdownServerB(){
		latency=20;
		failureWeight=0.1;
	}
	
	
	@Override
	public String toString(){
	 	return "ShutdownServerB";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);
		
	}
	


}
