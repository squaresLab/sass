package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerB;

public class DecreaseDimmerLevelB extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelB(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelB";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);
		
	}
	


}
