package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerA;

public class DecreaseTrafficLevelA extends DecreaseTrafficLevel {

	public DecreaseTrafficLevelA(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseTrafficLevelA";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}
	


}
