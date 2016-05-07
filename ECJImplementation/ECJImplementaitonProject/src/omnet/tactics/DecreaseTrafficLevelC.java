package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerC;

public class DecreaseTrafficLevelC extends DecreaseTrafficLevel {

	public DecreaseTrafficLevelC(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseTrafficLevelC";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}
	


}
