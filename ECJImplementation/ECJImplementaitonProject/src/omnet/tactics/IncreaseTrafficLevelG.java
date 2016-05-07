package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerG;

public class IncreaseTrafficLevelG extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelG(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelG";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerG.class);
		
	}
	


}
