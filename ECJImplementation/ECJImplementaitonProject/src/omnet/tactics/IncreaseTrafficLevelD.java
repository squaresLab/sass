package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerD;

public class IncreaseTrafficLevelD extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelD(){
		latency=5;
		failureWeight=0.01;
	}
	
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelD";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerD.class);
		
	}
	


}
