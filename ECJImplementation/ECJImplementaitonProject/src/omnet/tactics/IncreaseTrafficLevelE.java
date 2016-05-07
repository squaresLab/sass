package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerE;

public class IncreaseTrafficLevelE extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelE(){
		latency=5;
		failureWeight=0.01;
	}
	
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelE";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerE.class);
		
	}
	


}
