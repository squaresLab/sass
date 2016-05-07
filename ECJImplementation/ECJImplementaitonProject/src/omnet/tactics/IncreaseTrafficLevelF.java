package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerF;

public class IncreaseTrafficLevelF extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelF(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelF";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerF.class);
		
	}
	


}
