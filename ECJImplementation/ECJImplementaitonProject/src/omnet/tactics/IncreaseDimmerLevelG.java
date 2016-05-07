package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerG;

public class IncreaseDimmerLevelG extends IncreaseDimmerLevel {

	public IncreaseDimmerLevelG(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseDimmerLevelG";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerG.class);
		
	}
	


}
