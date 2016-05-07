package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerA;

public class IncreaseDimmerLevelA extends IncreaseDimmerLevel {

	public IncreaseDimmerLevelA(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseDimmerLevelA";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}
	


}
