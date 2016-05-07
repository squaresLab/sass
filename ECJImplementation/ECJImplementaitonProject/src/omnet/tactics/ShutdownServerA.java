package omnet.tactics;

import actions.AddServer;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Parameter;
import main.OmnetStateData;
import main.StateData;
import omnet.components.OmnetComponent;
import omnet.components.ServerA;

public class ShutdownServerA extends ShutdownServer {
	
	public ShutdownServerA(){
		latency=20;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "DeleteServerA";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}
	


}
