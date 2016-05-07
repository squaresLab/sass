package omnet.tactics;

import main.OmnetStateData;
import omnet.components.ServerD;

public class DecreaseDimmerLevelD extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelD(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelD";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerD.class);
		
	}
	


}
