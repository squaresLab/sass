package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerB;

public class DecreaseDimmerLevelB extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelB(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelB";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);
		
	}
	


}
