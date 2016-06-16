package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerC;

public class ShutdownServerC extends ShutdownServer {

	public ShutdownServerC(){
		latency=30;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "ShutdownServerC";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}
	


}
