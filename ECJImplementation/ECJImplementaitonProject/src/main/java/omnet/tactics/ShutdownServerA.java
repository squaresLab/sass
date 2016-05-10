package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerA;

public class ShutdownServerA extends ShutdownServer {
	
	public ShutdownServerA(){
		latency=20;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "ShutdownServerA";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}
	


}
