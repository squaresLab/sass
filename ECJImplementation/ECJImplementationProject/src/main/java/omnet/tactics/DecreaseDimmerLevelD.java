package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerD;

public class DecreaseDimmerLevelD extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelD(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelD";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerD.class);
		
	}
	


}
