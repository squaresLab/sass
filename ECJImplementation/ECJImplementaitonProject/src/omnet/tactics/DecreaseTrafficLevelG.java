package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerG;

public class DecreaseTrafficLevelG extends DecreaseTrafficLevel {

	public DecreaseTrafficLevelG(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseTrafficLevelG";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerG.class);
		
	}
	


}
