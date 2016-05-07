package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerE;

public class DecreaseTrafficLevelE extends DecreaseTrafficLevel {

	public DecreaseTrafficLevelE(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseTrafficLevelE";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerE.class);
		
	}
	


}
