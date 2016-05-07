package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerC;

public class IncreaseTrafficLevelC extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelC(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelC";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}
	


}
