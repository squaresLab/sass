package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerA;

public class IncreaseTrafficLevelA extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelA(){
		latency=5;
		failureWeight=0.01;
	}
	
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelA";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}
	


}
