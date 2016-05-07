package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerE;

public class IncreaseDimmerLevelE extends IncreaseDimmerLevel {

	public IncreaseDimmerLevelE(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseDimmerLevelE";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerE.class);
		
	}
	


}
