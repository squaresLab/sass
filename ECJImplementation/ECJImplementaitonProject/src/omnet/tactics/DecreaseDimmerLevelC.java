package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerC;

public class DecreaseDimmerLevelC extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelC(){
		latency=1;
		failureWeight=0.05;
	}
	
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelC";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}
	


}
