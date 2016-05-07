package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerG;

public class DecreaseDimmerLevelG extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelG(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelG";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerG.class);
		
	}
	


}
