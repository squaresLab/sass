package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerD;

public class DecreaseTrafficLevelD extends DecreaseTrafficLevel {

	public DecreaseTrafficLevelD(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseTrafficLevelD";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerD.class);
		
	}
	


}
