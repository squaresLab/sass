package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerF;

public class IncreaseDimmerLevelF extends IncreaseDimmerLevel {

	public IncreaseDimmerLevelF(){
		latency=1;
		failureWeight=0.05;
	}
	
	
	@Override
	public String toString(){
	 	return "IncreaseDimmerLevelF";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerF.class);
		
	}
	


}
