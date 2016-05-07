package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerB;

public class IncreaseTrafficLevelB extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelB(){
		latency=5;
		failureWeight=0.01;
	}
	
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelB";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);
		
	}
	


}
