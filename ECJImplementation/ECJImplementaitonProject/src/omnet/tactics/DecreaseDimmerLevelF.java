package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerF;

public class DecreaseDimmerLevelF extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelF(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelF";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerF.class);
		
	}
	


}
