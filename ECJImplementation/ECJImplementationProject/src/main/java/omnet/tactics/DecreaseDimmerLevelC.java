package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.omnet.components.ServerC;

public class DecreaseDimmerLevelC extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelC(){
		latency=1;
		failureWeight=0.05;
	}
	
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelC";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}
	
	public void callUndoTactic(OmnetStateData sd){
		
	}

}
