package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerB;

public class IncreaseDimmerLevelB extends IncreaseDimmerLevel {

	public IncreaseDimmerLevelB(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseDimmerLevelB";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);
		
	}
	


}
