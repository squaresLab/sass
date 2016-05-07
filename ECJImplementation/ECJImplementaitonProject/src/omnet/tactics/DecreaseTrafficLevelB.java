package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerB;

public class DecreaseTrafficLevelB extends DecreaseTrafficLevel {

	public DecreaseTrafficLevelB(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseTrafficLevelB";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);
		
	}
	


}
