package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerC;

public class IncreaseDimmerLevelC extends IncreaseDimmerLevel {

	public IncreaseDimmerLevelC(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "IncreaseDimmerLevelC";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}
	


}
