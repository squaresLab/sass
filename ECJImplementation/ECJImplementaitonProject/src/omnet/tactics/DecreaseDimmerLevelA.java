package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerA;

public class DecreaseDimmerLevelA extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelA(){
		latency=1;
		failureWeight=0.05;
	}
	
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelA";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}
	


}
