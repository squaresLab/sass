package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerE;

public class DecreaseDimmerLevelE extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelE(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelE";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerE.class);
		
	}
	


}
